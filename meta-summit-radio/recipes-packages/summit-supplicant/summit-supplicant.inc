SECTION = "Wireless"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://COPYING;md5=5ebcb90236d1ad640558c3d3cd3035df \
                    file://README;beginline=1;endline=56;md5=e3d2f6c2948991e37c1ca4960de84747 \
                    file://wpa_supplicant/wpa_supplicant.c;beginline=1;endline=12;md5=76306a95306fee9a976b0ac1be70f705"

PV = "${RADIO_VERSION}"

DEPENDS = "libnl openssl"
RRECOMMENDS:${PN} = "${PN}-passphrase ${PN}-cli"

inherit pkgconfig systemd 

SYSTEMD_SERVICE:${PN} = "wpa_supplicant.service wpa_supplicant-nl80211@.service wpa_supplicant-wired@.service"
SYSTEMD_AUTO_ENABLE = "disable"

SUMMIT_URI ?= "${SUMMIT_URI_BASE}"
SUMMIT_URI:summit-internal = "${SUMMIT_URI_BASE}/summit_supplicant/laird/${PV}"

SRC_URI += "${SUMMIT_URI}/summit_supplicant-src-${PV}.tar.gz;name=summit-supplicant-src"

S = "${WORKDIR}/summit_supplicant-${PV}"
B = "${S}/wpa_supplicant"

RPROVIDES:${PN} += "wpa-supplicant"
RREPLACES:${PN} += "wpa-supplicant"
RCONFLICTS:${PN} += "wpa-supplicant"

PROVIDES:${PN} += "wpa-supplicant-dbg"
RREPLACES:${PN} += "wpa-supplicant-dbg"
RCONFLICTS:${PN} += "wpa-supplicant-dbg"

RPROVIDES:${PN}-passphrase  += "wpa-supplicant-passphrase"
RREPLACES:${PN}-passphrase  += "wpa-supplicant-passphrase"
RCONFLICTS:${PN}-passphrase += "wpa-supplicant-passphrase"

RPROVIDES:${PN}-cli  += "wpa-supplicant-cli"
RREPLACES:${PN}-cli  += "wpa-supplicant-cli"
RCONFLICTS:${PN}-cli += "wpa-supplicant-cli"

PACKAGES:prepend = "${PN}-passphrase ${PN}-cli "
FILES:${PN}-passphrase = "${bindir}/wpa_passphrase"
FILES:${PN}-cli = "${sbindir}/wpa_cli"
FILES:${PN} += "${systemd_unitdir}/system/*"

SOLIBS = ".so*"
FILES_SOLIBSDEV = ""

do_configure () {
	cp -f ${DEFCONFIG} .config

	# For rebuild
	rm -f *.d dbus/*.d
}

do_compile:append () {
	${OBJCOPY} wpa_supplicant sdcsupp
}

EXTRA_OEMAKE = "'BINDIR=${sbindir}'"

do_install () {
	install -m 0755 -D -t ${D}${sbindir} wpa_cli sdcsupp
	install -m 0755 -D -t ${D}${bindir} wpa_passphrase

	if ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'true', 'false', d)}; then
		install -m 0644 -D -t ${D}${systemd_unitdir}/system systemd/*.service
	fi
}

pkg_postinst:${PN} () {
	# If we're offline, we don't need to do this.
	if [ "x$D" = "x" ]; then
		killall -q -HUP dbus-daemon || true
	fi
}
