SUMMARY = "Summit Wi-Fi Summit Supplicant"
SECTION = "Wireless"

LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://../COPYING;md5=279b4f5abb9c153c285221855ddb78cc"

SYSTEMD_SERVICE_${PN} = "wpa_supplicant.service wpa_supplicant-nl80211@.service wpa_supplicant-wired@.service"
SYSTEMD_AUTO_ENABLE = "disable"

HFP = "${@bb.utils.contains('TUNE_FEATURES', 'callconvention-hard', 'eabihf', 'eabi', d)}"

LRD_URI ?= "${LRD_URI_BASE}"
LRD_URI_summit-internal = "https://files.devops.rfpros.com/builds/linux/summit_supplicant/laird/${PV}"

SUPP_ARCH_x86 = "-x86"
SUPP_ARCH_x86-64 = "-x86_64"
SUPP_ARCH_powerpc64 = "-powerpc64-e5500"
SUPP_ARCH_arm = "-arm-${HFP}"
SUPP_ARCH_aarch64 = "-aarch64"

SRC_URI += "\
	${LRD_URI}/summit_supplicant${OPENSSLVER}${SUPP_ARCH}-${PV}.tar.bz2;subdir=src;name=summit-supplicant${OPENSSLVER}${SUPP_ARCH} \
	file://COPYING \
	"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

S = "${WORKDIR}/src"

RDEPENDS_${PN} = "dbus-lib libnl libnl-genl libcrypto libssl"
RDEPENDS_${PN}-passphrase = "dbus-lib libnl libnl-genl libcrypto libssl"

INSANE_SKIP_${PN} = "ldflags already-stripped"
INSANE_SKIP_${PN}-passphrase = "ldflags already-stripped"
INSANE_SKIP_${PN}-cli = "ldflags already-stripped"

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
FILES_${PN} += "${datadir}/dbus-1/system-services/* ${sysconfdir}/dbus-1/system.d/* ${systemd_unitdir}/system/*"

SOLIBS = ".so*"
FILES_SOLIBSDEV = ""

do_install () {
	install -D -t ${D}${sbindir} -m 755 ${S}/usr/sbin/sdcsupp
	install -D -t ${D}${sbindir} -m 755 ${S}/usr/sbin/wpa_cli

	install -D -t ${D}${bindir} -m 755 ${S}/usr/bin/wpa_passphrase

	install -D -t ${D}${sysconfdir}/dbus-1/system.d -m 644 ${S}/etc/dbus-1/system.d/wpa_supplicant.conf
	install -D -t ${D}${datadir}/dbus-1/system-services -m 644 ${S}/usr/share/dbus-1/system-services/*.service

	if ${@bb.utils.contains('DISTRO_FEATURES','systemd','true','false',d)}; then
		install -D -t ${D}${systemd_unitdir}/system -m 644 ${S}/usr/lib/systemd/system/*.service
	fi

	install -D -m 0644 ${S}${includedir}/wpa_ctrl.h ${D}${includedir}/wpa_ctrl.h
	install -D -m 0644 ${S}${libdir}/libwpa_client.so ${D}${libdir}/libwpa_client.so
}

pkg_postinst_${PN} () {
	# If we're offline, we don't need to do this.
	if [ "x$D" = "x" ]; then
		killall -q -HUP dbus-daemon || true
	fi
}
