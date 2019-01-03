SUMMARY = "Laird Sterling 60 Firmware SDIO-SDIO"
SECTION = "kernel"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit allarch lrd-url

SRC_URI += "${LRD_URI_BASE}/laird-sterling60-firmware-sdio-sdio-${PV}.tar.bz2"

SRC_URI[md5sum] = "8fc25ed1d8a796dd77228352e7c8d9c1"
SRC_URI[sha256sum] = "267a85790fbadf808092d99f3cb7a83973874cd9a0b975b375b4d917233a88b0"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

FILES_${PN} += "${nonarch_base_libdir}/*"

S = "${WORKDIR}"

do_install() {
	install -d  ${D}${nonarch_base_libdir}
	cp -r ${S}/lib/* ${D}${nonarch_base_libdir}
}
