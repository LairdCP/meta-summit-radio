SUMMARY = "Laird Sterling 60 Firmware SDIO-SDIO"
SECTION = "kernel"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit allarch lrd-url

SRC_URI += "${LRD_URI_BASE}/laird-sterling60-firmware-sdio-sdio-${PV}.tar.bz2"

SRC_URI[md5sum] = "dc3f66184f19f68c228d20926a43c8f3"
SRC_URI[sha256sum] = "a10f1ec236e283a770aca3ea33d2e11f71e0e723feb13b131b04a1caf9680d2c"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

FILES_${PN} += "${nonarch_base_libdir}/*"

S = "${WORKDIR}"

do_install() {
	install -d  ${D}${nonarch_base_libdir}
	cp -r ${S}/lib/* ${D}${nonarch_base_libdir}
}
