SUMMARY = "Laird Wi-Fi Backport"
SECTION = "kernel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

inherit module lrd-url

DEPENDS += "coreutils-native"

SRC_URI += "${LRD_URI_BASE}/${PN}-${PV}.tar.bz2"

SRC_URI[md5sum] = "e1bdb2f83fa86acd65c5f8ee019db8d4"
SRC_URI[sha256sum] = "194a3546beff23f17b0cba41f9ac472d594be2cc2e80f6f2d19cb6acb86434cf"

S = "${WORKDIR}/laird-backport-${PV}"

EXTRA_OEMAKE = "KLIB_BUILD=${STAGING_KERNEL_DIR} KLIB=${D} DESTDIR=${D} KERNEL_CONFIG=${STAGING_KERNEL_BUILDDIR}/.config"

do_compile_prepend() {
	rm -f ${S}/.kernel_config_md5
	oe_runmake CC=${BUILD_CC} defconfig-sterling60
}
