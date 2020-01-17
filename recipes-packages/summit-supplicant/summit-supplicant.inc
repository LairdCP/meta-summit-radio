SUMMARY = "Laird Wi-Fi Summit Supplicant"
SECTION = "Wireless"

LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://../COPYING;md5=279b4f5abb9c153c285221855ddb78cc"

inherit lrd-url

SYSTEMD_SERVICE_${PN} = "wpa_supplicant.service wpa_supplicant-nl80211@.service wpa_supplicant-wired@.service"
SYSTEMD_AUTO_ENABLE = "disable"

HFP = "${@bb.utils.contains('TUNE_FEATURES', 'callconvention-hard', 'eabihf', 'eabi', d)}"

SRC_URI += "file://COPYING"

SRC_URI_append_x86 = " ${LRD_URI_LOCAL}/summit_supplicant${OPENSSLVER}-x86-${PV}.tar.bz2;subdir=src "
SRC_URI_append_arm = " ${LRD_URI_LOCAL}/summit_supplicant${OPENSSLVER}-arm-${HFP}-${PV}.tar.bz2;subdir=src "
SRC_URI_append_aarch64 = " ${LRD_URI_LOCAL}/summit_supplicant${OPENSSLVER}-aarch64-eabihf-${PV}.tar.bz2;subdir=src "

do_configure[noexec] = "1"
do_compile[noexec] = "1"

S = "${WORKDIR}/src"

RDEPENDS_${PN} = "dbus-lib libnl-genl libssl libcrypto"

INSANE_SKIP_${PN} = "ldflags"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_PACKAGE_STRIP = "1"

ALLOW_EMPTY_${PN}-dev = "0"
ALLOW_EMPTY_${PN}-dbg = "0"

FILES_${PN} += "/usr/*"

do_install() {
	cp -r ${S}/* ${D}
}
