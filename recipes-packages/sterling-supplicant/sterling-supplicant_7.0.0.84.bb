SUMMARY = "Laird Wi-Fi Sterling Supplicant"
SECTION = "Wireless"

LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://README;md5=495cbce6008253de4b4d8f4cdfae9f4f"

inherit pkgconfig systemd lrd-url

SRC_URI += "\
	${LRD_URI_BASE}/sterling_supplicant-src-${PV}.tar.gz \
	file://defconfig \
	file://wpa-supplicant.sh \
	file://wpa_supplicant.conf \
	file://wpa_supplicant.conf-sane \
	file://99_wpa_supplicant \
	"

SRC_URI[md5sum] = "f1ad04f62719a7b14971c713f8edf8a8"
SRC_URI[sha256sum] = "b1ff34606a0259be448625ced21986fecdeccfb25474f3accecc7de03cd4a6da"

S = "${WORKDIR}/sterling_supplicant-${PV}"

do_unpack_append() {
    import subprocess, os.path
    s = d.getVar("S", True)
    cmd = "tar -xf sterling_supplicant-src.tar".split()
    subprocess.call(cmd, cwd=os.path.dirname(s))
}

RPROVIDES_${PN} += "wpa-supplicant"
RREPLACES_${PN} += "wpa-supplicant"
RCONFLICTS_${PN} += "wpa-supplicant"

RPROVIDES_${PN}-passphrase  += "wpa-supplicant-passphrase"
RREPLACES_${PN}-passphrase  += "wpa-supplicant-passphrase"
RCONFLICTS_${PN}-passphrase += "wpa-supplicant-passphrase"

RPROVIDES_${PN}-cli  += "wpa-supplicant-cli"
RREPLACES_${PN}-cli  += "wpa-supplicant-cli"
RCONFLICTS_${PN}-cli += "wpa-supplicant-cli"

DEPENDS = "dbus libnl"
RRECOMMENDS_${PN} = "sterling-supplicant-passphrase sterling-supplicant-cli"

PACKAGECONFIG ??= "openssl"
PACKAGECONFIG[gnutls] = ",,gnutls libgcrypt"
PACKAGECONFIG[openssl] = ",,openssl"

SYSTEMD_SERVICE_${PN} = "wpa_supplicant.service wpa_supplicant-nl80211@.service wpa_supplicant-wired@.service"
SYSTEMD_AUTO_ENABLE = "disable"

PACKAGES_prepend = "sterling-supplicant-passphrase sterling-supplicant-cli "
FILES_sterling-supplicant-passphrase = "${bindir}/wpa_passphrase"
FILES_sterling-supplicant-cli = "${sbindir}/wpa_cli"
FILES_${PN} += "${datadir}/dbus-1/system-services/*"
CONFFILES_${PN} += "${sysconfdir}/wpa_supplicant.conf"

do_configure () {
	${MAKE} -C wpa_supplicant clean
	install -m 0755 ${WORKDIR}/defconfig wpa_supplicant/.config
	echo "CFLAGS +=\"-I${STAGING_INCDIR}/libnl3\"" >> wpa_supplicant/.config
	echo "DRV_CFLAGS +=\"-I${STAGING_INCDIR}/libnl3\"" >> wpa_supplicant/.config
	
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
	install -d ${D}${sbindir}
	install -m 755 wpa_supplicant/wpa_supplicant ${D}${sbindir}
	install -m 755 wpa_supplicant/wpa_cli        ${D}${sbindir}

	install -d ${D}${bindir}
	install -m 755 wpa_supplicant/wpa_passphrase ${D}${bindir}

	install -d ${D}${docdir}/wpa_supplicant
	install -m 644 wpa_supplicant/README ${WORKDIR}/wpa_supplicant.conf ${D}${docdir}/wpa_supplicant

	install -d ${D}${sysconfdir}
	install -m 600 ${WORKDIR}/wpa_supplicant.conf-sane ${D}${sysconfdir}/wpa_supplicant.conf

	install -d ${D}${sysconfdir}/network/if-pre-up.d/
	install -d ${D}${sysconfdir}/network/if-post-down.d/
	install -d ${D}${sysconfdir}/network/if-down.d/
	install -m 755 ${WORKDIR}/wpa-supplicant.sh ${D}${sysconfdir}/network/if-pre-up.d/wpa-supplicant
	cd ${D}${sysconfdir}/network/ && \
	ln -sf ../if-pre-up.d/wpa-supplicant if-post-down.d/wpa-supplicant

	install -d ${D}/${sysconfdir}/dbus-1/system.d
	install -m 644 ${S}/wpa_supplicant/dbus/dbus-wpa_supplicant.conf ${D}/${sysconfdir}/dbus-1/system.d
	install -d ${D}/${datadir}/dbus-1/system-services
	install -m 644 ${S}/wpa_supplicant/dbus/*.service ${D}/${datadir}/dbus-1/system-services

	if ${@bb.utils.contains('DISTRO_FEATURES','systemd','true','false',d)}; then
		install -d ${D}/${systemd_unitdir}/system
		install -m 644 ${S}/wpa_supplicant/systemd/*.service ${D}/${systemd_unitdir}/system
	fi

	install -d ${D}/etc/default/volatiles
	install -m 0644 ${WORKDIR}/99_wpa_supplicant ${D}/etc/default/volatiles
}

pkg_postinst_sterling-supplicant () {
	# If we're offline, we don't need to do this.
	if [ "x$D" = "x" ]; then
		killall -q -HUP dbus-daemon || true
	fi
}
