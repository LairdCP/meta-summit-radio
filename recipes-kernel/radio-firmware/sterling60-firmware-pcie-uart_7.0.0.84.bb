SUMMARY = "Laird Sterling 60 Firmware PCIE-UART"
SECTION = "kernel"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit allarch lrd-url

SRC_URI += "${LRD_URI_BASE}/laird-sterling60-firmware-pcie-uart-${PV}.tar.bz2"

SRC_URI[md5sum] = "ea1b5ba8be0fdcbdd460cec880d863e3"
SRC_URI[sha256sum] = "1aedeb53069cdcf7572c7234372d3de9b8198c02f05a141275387038447a1b00"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

FILES_${PN} += "${nonarch_base_libdir}/*"

S = "${WORKDIR}"

do_install() {
	install -d  ${D}${nonarch_base_libdir}
	cp -r ${S}/lib/* ${D}${nonarch_base_libdir}
}
