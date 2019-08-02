SUMMARY = "Laird Sterling 60 Firmware PCIE-USB"
SECTION = "kernel"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit allarch lrd-url

SRC_URI += "${LRD_URI_BASE}/laird-sterling60-firmware-pcie-usb-${PV}.tar.bz2"

SRC_URI[md5sum] = "d3002f8ac84284601b177797c978e8dc"
SRC_URI[sha256sum] = "ad731d3779dd84da3d257d0e570e2bc4883f030e9f77a89249be67d28630ad9e"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

FILES_${PN} += "${nonarch_base_libdir}/*"

S = "${WORKDIR}"

do_install() {
	install -d  ${D}${nonarch_base_libdir}
	cp -r ${S}/lib/* ${D}${nonarch_base_libdir}
}
