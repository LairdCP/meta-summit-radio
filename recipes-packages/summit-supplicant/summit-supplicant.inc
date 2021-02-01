SUMMARY = "Laird Connectivity Wi-Fi Summit Supplicant"
SECTION = "Wireless"

LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://../COPYING;md5=279b4f5abb9c153c285221855ddb78cc"

inherit lrd-url

SYSTEMD_SERVICE_${PN} = "wpa_supplicant.service wpa_supplicant-nl80211@.service wpa_supplicant-wired@.service"
SYSTEMD_AUTO_ENABLE = "disable"

HFP = "${@bb.utils.contains('TUNE_FEATURES', 'callconvention-hard', 'eabihf', 'eabi', d)}"

SRC_URI += "file://COPYING"

SRC_URI_append_x86 = " ${LRD_URI_LOCAL}/summit_supplicant${OPENSSLVER}-x86-${PV}.tar.bz2;subdir=src "
SRC_URI_append_x86-64 = " ${LRD_URI_LOCAL}/summit_supplicant${OPENSSLVER}-x86-64-${PV}.tar.bz2;subdir=src "
SRC_URI_append_powerpc64 = " ${LRD_URI_LOCAL}/summit_supplicant${OPENSSLVER}-powerpc-e5500-${PV}.tar.bz2;subdir=src "
SRC_URI_append_arm = " ${LRD_URI_LOCAL}/summit_supplicant${OPENSSLVER}-arm-${HFP}-${PV}.tar.bz2;subdir=src "
SRC_URI_append_aarch64 = " ${LRD_URI_LOCAL}/summit_supplicant${OPENSSLVER}-aarch64-${PV}.tar.bz2;subdir=src "

do_configure[noexec] = "1"
do_compile[noexec] = "1"

S = "${WORKDIR}/src"

RDEPENDS_${PN} = "dbus-lib libnl libnl-genl libcrypto libssl"
RDEPENDS_${PN}-passphrase = "dbus-lib libnl libnl-genl libcrypto libssl"

INSANE_SKIP_${PN} = "ldflags"
INSANE_SKIP_${PN}-passphrase = "ldflags"
INSANE_SKIP_${PN}-cli = "ldflags"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_PACKAGE_STRIP = "1"

ALLOW_EMPTY_${PN}-dev = "0"
ALLOW_EMPTY_${PN}-dbg = "0"

RPROVIDES_${PN} += "wpa-supplicant"
RREPLACES_${PN} += "wpa-supplicant"
RCONFLICTS_${PN} += "wpa-supplicant"

RPROVIDES_${PN}-passphrase  += "wpa-supplicant-passphrase"
RREPLACES_${PN}-passphrase  += "wpa-supplicant-passphrase"
RCONFLICTS_${PN}-passphrase += "wpa-supplicant-passphrase"

RPROVIDES_${PN}-cli  += "wpa-supplicant-cli"
RREPLACES_${PN}-cli  += "wpa-supplicant-cli"
RCONFLICTS_${PN}-cli += "wpa-supplicant-cli"

PACKAGES_prepend = "${PN}-passphrase ${PN}-cli "
FILES_${PN}-passphrase = "${bindir}/wpa_passphrase"
FILES_${PN}-cli = "${sbindir}/wpa_cli"
FILES_${PN} += "${datadir}/dbus-1/system-services/* {sysconfdir}/dbus-1/system.d/* ${systemd_unitdir}/system/*"

do_install () {
	install -D -t ${D}${sbindir} -m 755 ${S}/usr/sbin/sdcsupp
	install -D -t ${D}${sbindir} -m 755 ${S}/usr/sbin/wpa_cli

	install -D -t ${D}${bindir} -m 755 ${S}/usr/bin/wpa_passphrase

	install -D -t ${D}${sysconfdir}/dbus-1/system.d -m 644 ${S}/etc/dbus-1/system.d/wpa_supplicant.conf
	install -D -t ${D}${datadir}/dbus-1/system-services -m 644 ${S}/usr/share/dbus-1/system-services/*.service

	if ${@bb.utils.contains('DISTRO_FEATURES','systemd','true','false',d)}; then
		install -D -t ${D}${systemd_unitdir}/system -m 644 ${S}/usr/lib/systemd/system/*.service
	fi
}

pkg_postinst_${PN} () {
	# If we're offline, we don't need to do this.
	if [ "x$D" = "x" ]; then
		killall -q -HUP dbus-daemon || true
	fi

}
