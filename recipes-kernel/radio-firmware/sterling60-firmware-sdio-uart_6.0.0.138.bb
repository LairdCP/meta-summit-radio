SUMMARY = "Laird Sterling 60 Firmware SDIO-UART"
SECTION = "kernel"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit allarch lrd-url

SRC_URI += "${LRD_URI_BASE}/laird-sterling60-firmware-sdio-uart-${PV}.tar.bz2"

SRC_URI[md5sum] = "cc1af882c1c92a4d5cae9d192148785d"
SRC_URI[sha256sum] = "e1efcc7557925417430996a70b4e4ccc0291c5287ea8ec7e7e716842e7d9032d"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

FILES_${PN} += "${nonarch_base_libdir}/*"

S = "${WORKDIR}"

do_install() {
	install -d  ${D}${nonarch_base_libdir}
	cp -r ${S}/lib/* ${D}${nonarch_base_libdir}
}
