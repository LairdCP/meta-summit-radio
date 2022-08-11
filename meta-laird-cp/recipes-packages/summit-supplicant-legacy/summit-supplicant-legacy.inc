SUMMARY = "Laird Connectivity Wi-Fi Summit Supplicant Legacy"
SECTION = "Wireless"

LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://../COPYING;md5=279b4f5abb9c153c285221855ddb78cc"

SYSTEMD_SERVICE:${PN} = "wpa_supplicant.service wpa_supplicant-nl80211@.service wpa_supplicant-wired@.service"
SYSTEMD_AUTO_ENABLE = "disable"

HFP = "${@bb.utils.contains('TUNE_FEATURES', 'callconvention-hard', 'eabihf', 'eabi', d)}"

LRD_URI ?= "${LRD_URI_BASE}"
LRD_URI:laird-internal = "https://files.devops.rfpros.com/builds/linux/summit_supplicant/laird/${PV}"

SUPP_ARCH = "-unknown"
SUPP_ARCH:arm = "-arm-${HFP}"

SRC_URI = "\
	${LRD_URI}/summit_supplicant${OPENSSLVER}${SUPP_ARCH}-${PV}.tar.bz2;subdir=src;name=summit-supplicant${OPENSSLVER}${SUPP_ARCH} \
	file://COPYING \
	"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

S = "${WORKDIR}/src"

RDEPENDS:${PN} = "libnl libnl-genl libcrypto libssl"

INSANE_SKIP:${PN} = "ldflags already-stripped"

RPROVIDES:${PN} += "wpa-supplicant"
RREPLACES:${PN} += "wpa-supplicant"
RCONFLICTS:${PN} += "wpa-supplicant"

FILES:${PN} += "${systemd_unitdir}/system/*"

do_install () {
	install -D -t ${D}${sbindir} -m 755 ${S}/usr/sbin/sdcsupp
	install -D -t ${D}${bindir} -m 0644 ${S}/usr/bin/*

	install -D -t ${D}${libdir} -m 0644 ${S}/usr/lib/libsdc_sdk.so.1*
	ln -rsf ${D}${libdir}/libsdc_sdk.so.1 ${D}/usr/lib/libsdc_sdk.so
	install -D -t ${D}${includedir} -m 0644 ${S}/usr/include/*

	if ${@bb.utils.contains('DISTRO_FEATURES','systemd','true','false',d)}; then
		install -D -t ${D}${systemd_unitdir}/system -m 644 ${S}/usr/lib/systemd/system/*.service
	fi
}

pkg_postinst:${PN} () {
	# If we're offline, we don't need to do this.
	if [ "x$D" = "x" ]; then
		killall -q -HUP dbus-daemon || true
	fi
}
