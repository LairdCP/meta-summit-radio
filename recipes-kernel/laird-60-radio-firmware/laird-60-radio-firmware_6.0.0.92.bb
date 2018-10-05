SUMMARY = "Laird Sterling 60 Firmware"
SECTION = "kernel"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit allarch lrd-url

SRC_URI += "${LRD_URI_BASE}/${PN}-${PV}.tar.bz2"

SRC_URI[md5sum] = "74cbd09e08e4680e1757502105cfe6fc"
SRC_URI[sha256sum] = "d0ff931fb1c8e284ad3fdba88ad1fc149f57ed8ab43913186d500183845b640b"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

FILES_${PN} += "${nonarch_base_libdir}/*"

S = "${WORKDIR}"

do_install() {
	install -d  ${D}${nonarch_base_libdir}
	cp -r ${S}/lib/* ${D}${nonarch_base_libdir}
}
