SUMMARY = "Laird Wi-Fi Backport"
SECTION = "kernel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

inherit module lrd-url

DEPENDS += "coreutils-native"

SRC_URI += "${LRD_URI_BASE}/${PN}-${PV}.tar.bz2"

SRC_URI[md5sum] = "2511ad69729d308da31636848d0c1b55"
SRC_URI[sha256sum] = "791da482395f529a0aa8f935c23c79193634f8fc24498faa18ed6a9912099705"

S = "${WORKDIR}/laird-backport-${PV}"

EXTRA_OEMAKE = "KLIB_BUILD=${STAGING_KERNEL_DIR} KLIB=${D} DESTDIR=${D} KMODDIR=. KERNEL_CONFIG=${STAGING_KERNEL_BUILDDIR}/.config"

do_compile_prepend() {
	rm -f ${S}/.kernel_config_md5
	oe_runmake CC=${BUILD_CC} defconfig-sterling60
}

do_install_append() {
    # backports need to go into updates/ to be detected correctly
    KD=`cd  ${D}/lib/modules; ls`
    echo KV $KD
    mkdir -v ${D}/lib/modules/${KD}/updates
    mv -v ${D}/lib/modules/${KD}/{compat,drivers,net} ${D}/lib/modules/${KD}/updates
}
