SUMMARY = "Laird Sterling 60 Firmware PCIE-USB"
SECTION = "kernel"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit allarch lrd-url

SRC_URI += "${LRD_URI_BASE}/laird-sterling60-firmware-pcie-usb-${PV}.tar.bz2"

SRC_URI[md5sum] = "483bad22f90dfa7009a561d32dd2cf19"
SRC_URI[sha256sum] = "c639be0e23880a3377e5ae643e7343f611b8182b313971b7e44efe97da05e345"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

FILES_${PN} += "${nonarch_base_libdir}/*"

S = "${WORKDIR}"

do_install() {
	install -d  ${D}${nonarch_base_libdir}
	cp -r ${S}/lib/* ${D}${nonarch_base_libdir}
}
