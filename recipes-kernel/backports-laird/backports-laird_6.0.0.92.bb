SUMMARY = "Laird Wi-Fi Backport"
SECTION = "kernel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

inherit module lrd-url

DEPENDS += "coreutils-native"
RDEPENDS_${PN} += "laird-60-radio-firmware"

SRC_URI += "${LRD_URI_BASE}/${PN}-${PV}.tar.bz2"

SRC_URI[md5sum] = "a7c5403c67ad89e72a2025b0bff107f6"
SRC_URI[sha256sum] = "08d75ed9cd6b61783f1b97dbbc05810a5d87023c71517013fa266bcfddc22632"

S = "${WORKDIR}/laird-backport-${PV}"

EXTRA_OEMAKE = "KLIB_BUILD=${STAGING_KERNEL_DIR} KLIB=${D} DESTDIR=${D} KMODDIR=. KERNEL_CONFIG=${STAGING_KERNEL_BUILDDIR}/.config"

do_compile_prepend() {
	rm -f ${S}/.kernel_config_md5
	oe_runmake CC=${BUILD_CC} defconfig-sterling60
}
