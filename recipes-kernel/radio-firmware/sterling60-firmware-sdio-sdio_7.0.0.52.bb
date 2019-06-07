SUMMARY = "Laird Sterling 60 Firmware SDIO-SDIO"
SECTION = "kernel"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit allarch lrd-url

SRC_URI += "${LRD_URI_BASE}/laird-sterling60-firmware-sdio-sdio-${PV}.tar.bz2"

SRC_URI[md5sum] = "1369f5bf2a0e45658019e6fd1bd6ab8c"
SRC_URI[sha256sum] = "0f0d6efa6e5618c0e4e6fe94c8439c8a1ed458323e208c6ed8253f054d20dc24"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

FILES_${PN} += "${nonarch_base_libdir}/*"

S = "${WORKDIR}"

do_install() {
	install -d  ${D}${nonarch_base_libdir}
	cp -r ${S}/lib/* ${D}${nonarch_base_libdir}
}
