SECTION = "kernel"

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

inherit module

DEPENDS += "coreutils-native bison-native flex-native"

S = "${WORKDIR}/laird-backport-${PV}"

LRD_URI ?= "${LRD_URI_BASE}"
LRD_URI:summit-internal = "https://files.devops.rfpros.com/builds/linux/backports/laird/${PV}"

SRC_URI += "${LRD_URI}/backports-laird-${PV}.tar.bz2;name=summit-backports"

EXTRA_OEMAKE = "\
	KLIB_BUILD=${STAGING_KERNEL_DIR} \
	KLIB=${D} \
	DESTDIR=${D} \
	KERNEL_CONFIG=${STAGING_KERNEL_BUILDDIR}/.config \
	KMODDIR=updates \
	INSTALL_MOD_DIR=updates \
	BACKPORT_DIR=${S} \
	M=${S} \
	"

do_compile:prepend () {
	rm -f ${S}/.kernel_config_md5
	unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS
	oe_runmake CC=${BUILD_CC} ${BACKPORTS_CONFIG}
}

do_install:prepend () {
	cd ${STAGING_KERNEL_DIR}
}

do_install:append () {
	cp -R --no-dereference --preserve=mode,links -v ${S}/include ${D}${includedir}/${BPN}
	cp -R --no-dereference --preserve=mode,links -v ${S}/backport-include ${D}${includedir}/${BPN}
	rm -f ${D}${includedir}/${BPN}/backport-include/linux/module.h
}
