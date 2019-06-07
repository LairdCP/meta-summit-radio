SUMMARY = "Laird Sterling 60 Firmware SDIO-UART"
SECTION = "kernel"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit allarch lrd-url

SRC_URI += "${LRD_URI_BASE}/laird-sterling60-firmware-sdio-uart-${PV}.tar.bz2"

SRC_URI[md5sum] = "8f523019687dfdf9c3ba0fffdeae59ed"
SRC_URI[sha256sum] = "43352dcf245a53c370122a31e49bcd19978915dd4b4cf059d086fa3cdb98d7dd"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

FILES_${PN} += "${nonarch_base_libdir}/*"

S = "${WORKDIR}"

do_install() {
	install -d  ${D}${nonarch_base_libdir}
	cp -r ${S}/lib/* ${D}${nonarch_base_libdir}
}
