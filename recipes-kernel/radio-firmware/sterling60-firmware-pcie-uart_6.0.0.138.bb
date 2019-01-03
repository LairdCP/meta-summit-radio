SUMMARY = "Laird Sterling 60 Firmware PCIE-UART"
SECTION = "kernel"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit allarch lrd-url

SRC_URI += "${LRD_URI_BASE}/laird-sterling60-firmware-pcie-uart-${PV}.tar.bz2"

SRC_URI[md5sum] = "6637ed2a397e387c8aa8a8ac9d7f4252"
SRC_URI[sha256sum] = "8e5d322a082d98c77eca71a587d8f75d0377e19cb00f2cdfbaead39041cdbfca"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

FILES_${PN} += "${nonarch_base_libdir}/*"

S = "${WORKDIR}"

do_install() {
	install -d  ${D}${nonarch_base_libdir}
	cp -r ${S}/lib/* ${D}${nonarch_base_libdir}
}
