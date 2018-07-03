SUMMARY = "Laird Wi-Fi Backport"
SECTION = "kernel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

inherit module

SRC_URI += "https://raw.github.com/LairdCP/Release-Packages/master/ST60/5.0.0.x/${PV}/backports-laird-${PV}.tar.bz2"

SRC_URI[md5sum] = "b3d04515ccd24a0dd00f1082d4b8bb2a"
SRC_URI[sha256sum] = "97ec3f425ae480ec186121234cda13c3c21e3ca0a58237bf9309479766c2df01"

EXTRA_OEMAKE = "KLIB_BUILD=${STAGING_KERNEL_DIR} KLIB=${D} DESTDIR=${D} KMODDIR=. KERNEL_CONFIG=${STAGING_KERNEL_BUILDDIR}/.config"

do_compile_prepend() {
	oe_runmake CC=${BUILD_CC} defconfig-sterling60
}
