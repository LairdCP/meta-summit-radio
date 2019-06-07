SUMMARY = "Laird Sterling 60 Firmware USB-UART"
SECTION = "kernel"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit allarch lrd-url

SRC_URI += "${LRD_URI_BASE}/laird-sterling60-firmware-usb-uart-${PV}.tar.bz2"

SRC_URI[md5sum] = "fed3eb5992c8a0ee9e903a2d83310edd"
SRC_URI[sha256sum] = "bed92a13bb611d037f0c9f2c84ecbebdce3b852d5143e33dd1d2b7cf27a6d385"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

FILES_${PN} += "${nonarch_base_libdir}/*"

S = "${WORKDIR}"

do_install() {
	install -d  ${D}${nonarch_base_libdir}
	cp -r ${S}/lib/* ${D}${nonarch_base_libdir}
}
