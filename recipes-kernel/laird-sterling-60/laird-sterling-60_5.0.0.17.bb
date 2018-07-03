SUMMARY = "Laird Sterling 60 Firmware"
SECTION = "kernel"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI += "https://raw.github.com/LairdCP/Release-Packages/master/ST60/5.0.0.x/${PV}/${PN}-${PV}.tar.bz2;subdir=${BP}"

SRC_URI[md5sum] = "9c5045e37371f89e93baa10c9043ca97"
SRC_URI[sha256sum] = "61465b671978fe8d914c47d438dbb9b3a304396f136cc44f03886eec92367ba2"

inherit allarch

do_configure[noexec] = "1"
do_compile[noexec] = "1"

FILES_${PN} += "${nonarch_base_libdir}/*"

do_install() {
	install -d  ${D}${nonarch_base_libdir}
	cp -r ${S}/lib/* ${D}${nonarch_base_libdir}
}
