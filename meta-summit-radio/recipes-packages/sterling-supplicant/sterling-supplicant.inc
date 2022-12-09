SECTION = "Wireless"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://COPYING;md5=5ebcb90236d1ad640558c3d3cd3035df \
                    file://README;beginline=1;endline=56;md5=e3d2f6c2948991e37c1ca4960de84747 \
                    file://wpa_supplicant/wpa_supplicant.c;beginline=1;endline=12;md5=76306a95306fee9a976b0ac1be70f705"

DEPENDS = "dbus libnl"
RRECOMMENDS:${PN} = "${PN}-passphrase ${PN}-cli"

PACKAGECONFIG ??= "openssl"
PACKAGECONFIG[gnutls] = ",,gnutls libgcrypt"
PACKAGECONFIG[openssl] = ",,openssl"

inherit pkgconfig systemd

SYSTEMD_SERVICE:${PN} = "wpa_supplicant.service wpa_supplicant-nl80211@.service wpa_supplicant-wired@.service"
SYSTEMD_AUTO_ENABLE = "disable"

LRD_URI ?= "${LRD_URI_BASE}"
LRD_URI:summit-internal = "https://files.devops.rfpros.com/builds/linux/sterling_supplicant/src/${PV}"

SRC_URI += "\
	${LRD_URI}/sterling_supplicant-src-${PV}.tar.gz;name=sterling-supplicant \
	file://wpa-supplicant.sh \
	file://wpa_supplicant.conf \
	file://wpa_supplicant.conf-sane \
	file://99_wpa_supplicant \
	"

S = "${WORKDIR}/sterling_supplicant-${PV}"

RPROVIDES:${PN} += "wpa-supplicant"
RREPLACES:${PN} += "wpa-supplicant"
RCONFLICTS:${PN} += "wpa-supplicant"

RPROVIDES:${PN}-passphrase  += "wpa-supplicant-passphrase"
RREPLACES:${PN}-passphrase  += "wpa-supplicant-passphrase"
RCONFLICTS:${PN}-passphrase += "wpa-supplicant-passphrase"

RPROVIDES:${PN}-cli  += "wpa-supplicant-cli"
RREPLACES:${PN}-cli  += "wpa-supplicant-cli"
RCONFLICTS:${PN}-cli += "wpa-supplicant-cli"

PACKAGES:prepend = "${PN}-passphrase ${PN}-cli "
FILES:${PN}-passphrase = "${bindir}/wpa_passphrase"
FILES:${PN}-cli = "${sbindir}/wpa_cli"
FILES:${PN} += "${datadir}/dbus-1/system-services/*"
CONFFILES:${PN} += "${sysconfdir}/wpa_supplicant.conf"

do_configure () {
	${MAKE} -C wpa_supplicant clean
	install -m 0755 laird/config_laird wpa_supplicant/.config

	if echo "${PACKAGECONFIG}" | grep -qw "openssl"; then
		ssl=openssl
	elif echo "${PACKAGECONFIG}" | grep -qw "gnutls"; then
		ssl=gnutls
	fi
	if [ -n "$ssl" ]; then
		sed -i "s/%ssl%/$ssl/" wpa_supplicant/.config
	fi

	# For rebuild
	rm -f wpa_supplicant/*.d wpa_supplicant/dbus/*.d
}

export EXTRA_CFLAGS = "${CFLAGS}"
export BINDIR = "${sbindir}"

do_compile () {
	unset CFLAGS CPPFLAGS CXXFLAGS
	sed -e "s:CFLAGS\ =.*:& \$(EXTRA_CFLAGS):g" -i ${S}/src/lib.rules
	oe_runmake -C wpa_supplicant
}

do_install () {
	install -D -t ${D}${sbindir} -m 755 wpa_supplicant/wpa_supplicant
	install -D -t ${D}${sbindir} -m 755 wpa_supplicant/wpa_cli
	install -D -t ${D}${bindir} -m 755 wpa_supplicant/wpa_passphrase

	install -D -t ${D}${docdir}/wpa_supplicant -m 644 wpa_supplicant/README ${WORKDIR}/wpa_supplicant.conf
	install -D -m 600 ${WORKDIR}/wpa_supplicant.conf-sane ${D}${sysconfdir}/wpa_supplicant.conf

	install -d ${D}${sysconfdir}/network/if-pre-up.d/
	install -d ${D}${sysconfdir}/network/if-post-down.d/
	install -d ${D}${sysconfdir}/network/if-down.d/

	install -m 755 ${WORKDIR}/wpa-supplicant.sh ${D}${sysconfdir}/network/if-pre-up.d/wpa-supplicant
	ln -rsf ${D}${sysconfdir}/network/if-pre-up.d/wpa-supplicant ${D}${sysconfdir}/network/if-post-down.d/wpa-supplicant

	install -D -t ${D}/${sysconfdir}/dbus-1/system.d -m 644 ${S}/wpa_supplicant/dbus/dbus-wpa_supplicant.conf
	install -D -t ${D}/${datadir}/dbus-1/system-services -m 644 ${S}/wpa_supplicant/dbus/*.service

	if ${@bb.utils.contains('DISTRO_FEATURES','systemd','true','false',d)}; then
		install -D -t ${D}/${systemd_unitdir}/system -m 644 ${S}/wpa_supplicant/systemd/*.service
	fi

	install -D -t ${D}/etc/default/volatiles -m 0644 ${WORKDIR}/99_wpa_supplicant
}

pkg_postinst:${PN} () {
	# If we're offline, we don't need to do this.
	if [ "x$D" = "x" ]; then
		killall -q -HUP dbus-daemon || true
	fi
}
