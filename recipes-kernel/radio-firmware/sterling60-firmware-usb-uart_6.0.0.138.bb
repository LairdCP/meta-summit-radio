SUMMARY = "Laird Sterling 60 Firmware USB-UART"
SECTION = "kernel"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit allarch lrd-url

SRC_URI += "${LRD_URI_BASE}/laird-sterling60-firmware-usb-uart-${PV}.tar.bz2"

SRC_URI[md5sum] = "445c8dfaa5193ac31b79186a09517140"
SRC_URI[sha256sum] = "9a3c8e0b4cf6cecc115ebfe18627c24f66e77c7d26e5376b536323765c0ee93c"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

FILES_${PN} += "${nonarch_base_libdir}/*"

S = "${WORKDIR}"

do_install() {
	install -d  ${D}${nonarch_base_libdir}
	cp -r ${S}/lib/* ${D}${nonarch_base_libdir}
}
