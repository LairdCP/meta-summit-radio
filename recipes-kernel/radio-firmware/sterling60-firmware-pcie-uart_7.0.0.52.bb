SUMMARY = "Laird Sterling 60 Firmware PCIE-UART"
SECTION = "kernel"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit allarch lrd-url

SRC_URI += "${LRD_URI_BASE}/laird-sterling60-firmware-pcie-uart-${PV}.tar.bz2"

SRC_URI[md5sum] = "ff3a85dd5f9355098ef68100ec13c822"
SRC_URI[sha256sum] = "1ddc994047eb6566e0681e1a019fa6310a5b1154c48af1621a32214adba546d9"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

FILES_${PN} += "${nonarch_base_libdir}/*"

S = "${WORKDIR}"

do_install() {
	install -d  ${D}${nonarch_base_libdir}
	cp -r ${S}/lib/* ${D}${nonarch_base_libdir}
}
