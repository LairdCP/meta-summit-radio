SUMMARY = "Laird Sterling 60 Firmware USB-UART"
SECTION = "kernel"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit allarch lrd-url

SRC_URI += "${LRD_URI_BASE}/laird-sterling60-firmware-usb-uart-${PV}.tar.bz2"

SRC_URI[md5sum] = "ae3187d7f111b43b0a8199c50d19e1d9"
SRC_URI[sha256sum] = "e807cbdd6ba27cc8d24186785bba5aa92c00c5dd4fdca4a113b9207297b3c5c1"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

FILES_${PN} += "${nonarch_base_libdir}/*"

S = "${WORKDIR}"

do_install() {
	install -d  ${D}${nonarch_base_libdir}
	cp -r ${S}/lib/* ${D}${nonarch_base_libdir}
}
