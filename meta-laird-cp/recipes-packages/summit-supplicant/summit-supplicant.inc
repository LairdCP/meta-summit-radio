SUMMARY = "Laird Connectivity Wi-Fi Summit Supplicant"
SECTION = "Wireless"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://../COPYING;md5=279b4f5abb9c153c285221855ddb78cc"

SYSTEMD_SERVICE:${PN} = "wpa_supplicant.service wpa_supplicant-nl80211@.service wpa_supplicant-wired@.service"
SYSTEMD_AUTO_ENABLE = "disable"

HFP = "${@bb.utils.contains('TUNE_FEATURES', 'callconvention-hard', 'eabihf', 'eabi', d)}"

LRD_URI ?= "${LRD_URI_BASE}"
LRD_URI:laird-internal = "https://files.devops.rfpros.com/builds/linux/summit_supplicant/laird/${PV}"

SUPP_ARCH:x86 = "-x86"
SUPP_ARCH:x86-64 = "-x86_64"
SUPP_ARCH:powerpc64 = "-powerpc64-e5500"
SUPP_ARCH:arm = "-arm-${HFP}"
SUPP_ARCH:aarch64 = "-aarch64"

SRC_URI = "\
	${LRD_URI}/summit_supplicant${OPENSSLVER}${SUPP_ARCH}-${PV}.tar.bz2;subdir=src;name=summit-supplicant${OPENSSLVER}${SUPP_ARCH} \
	file://COPYING \
	"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

S = "${WORKDIR}/src"

RDEPENDS:${PN} = "dbus-lib libnl libnl-genl libcrypto libssl"
RDEPENDS:${PN}-passphrase = "dbus-lib libnl libnl-genl libcrypto libssl"

INSANE_SKIP:${PN} = "ldflags already-stripped"
INSANE_SKIP:${PN}-passphrase = "ldflags already-stripped"
INSANE_SKIP:${PN}-cli = "ldflags already-stripped"

ALLOW_EMPTY:${PN}-dev = "0"
ALLOW_EMPTY:${PN}-dbg = "0"

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
FILES:${PN} += "${datadir}/dbus-1/system-services/* ${sysconfdir}/dbus-1/system.d/* ${systemd_unitdir}/system/*"

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

pkg_postinst:${PN} () {
	# If we're offline, we don't need to do this.
	if [ "x$D" = "x" ]; then
		killall -q -HUP dbus-daemon || true
	fi
}
