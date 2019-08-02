SUMMARY = "Laird Sterling 60 Firmware SDIO-UART"
SECTION = "kernel"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit allarch lrd-url

SRC_URI += "${LRD_URI_BASE}/laird-sterling60-firmware-sdio-uart-${PV}.tar.bz2"

SRC_URI[md5sum] = "0e3ce96a9c03033c99c250fca3f57306"
SRC_URI[sha256sum] = "5b5075af2a51e22dd413a23c7f170f0349b7074ca73d2c83057986f910a15e6b"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

FILES_${PN} += "${nonarch_base_libdir}/*"

S = "${WORKDIR}"

do_install() {
	install -d  ${D}${nonarch_base_libdir}
	cp -r ${S}/lib/* ${D}${nonarch_base_libdir}
}
