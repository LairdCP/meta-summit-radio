SECTION = "kernel"

inherit allarch

LRD_URI ?= "${LRD_URI_BASE}"
LRD_URI:summit-internal = "https://files.devops.rfpros.com/builds/linux/firmware/${PV}"

SRC_URI = "${LRD_URI}/laird-${BPN}-${PV}.tar.bz2;name=${BPN}"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

FILES:${PN} += "${nonarch_base_libdir}"

S = "${WORKDIR}"

ALLOW_EMPTY:${PN}-dev = "0"
ALLOW_EMPTY:${PN}-dbg = "0"

do_install() {
	install -d  ${D}${nonarch_base_libdir}
	cp -r ${S}/lib/* ${D}${nonarch_base_libdir}
}
