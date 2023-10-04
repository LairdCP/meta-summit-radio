SECTION = "kernel"

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

PV = "${RADIO_VERSION}"

inherit module cml1

DEPENDS += "coreutils-native bison-native flex-native kern-tools-native"

S = "${WORKDIR}/laird-backport-${PV}"

SUMMIT_URI ?= "${SUMMIT_URI_BASE}"
SUMMIT_URI:summit-internal = "${SUMMIT_URI_BASE}/backports/laird/${PV}"

SRC_URI += "${SUMMIT_URI}/backports-laird-${PV}.tar.bz2;name=summit-backports"

EXTRA_OEMAKE = "\
	KLIB_BUILD=${STAGING_KERNEL_DIR} \
	KLIB=${D} \
	DESTDIR=${D} \
	KERNEL_CONFIG=${STAGING_KERNEL_BUILDDIR}/.config \
	KMODDIR=updates \
	INSTALL_MOD_DIR=updates \
	M=${S} \
	"

KERNEL_MODULE_PACKAGE_SUFFIX = "-backports-${KERNEL_VERSION}"

KCONFIG_CONFIG_COMMAND = "${EXTRA_OEMAKE} CC=${BUILD_CC} menuconfig"

do_configure () {
	rm -f ${S}/.kernel_config_md5

	# allow user to define their own defconfig in bbappend, taken from kernel.bbclass
	if [ "${S}" != "${B}" ] && [ -f "${S}/.config" ] && [ ! -f "${B}/.config" ]; then
		mv "${S}/.config" "${B}/.config"
	fi

	# Copy defconfig to .config if .config does not exist. This allows
	# recipes to manage the .config themselves in do_configure:prepend().
	if [ -f "${WORKDIR}/defconfig" ] && [ ! -f "${B}/.config" ]; then
		cp "${WORKDIR}/defconfig" "${B}/.config"
	fi

	# Copy in-tree defconfig to .config if .config does not exist.
	if [ ! -f "${B}/.config" ]; then
		cp "${S}/defconfigs/${BACKPORTS_CONFIG}" "${B}/.config"
	fi

	merge_config.sh -m .config ${@" ".join(find_cfgs(d))}

	unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS
	oe_runmake CC=${BUILD_CC} olddefconfig
}

do_savedefconfig () {
	bbplain "Saving defconfig to:\n${B}/defconfig"
	unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS
	oe_runmake -C ${B} CC=${BUILD_CC} savedefconfig
}

do_savedefconfig[nostamp] = "1"
addtask savedefconfig after do_configure

do_install:prepend () {
	cd ${STAGING_KERNEL_DIR}
}

do_install:append () {
	cp -R --no-dereference --preserve=mode,links -v ${S}/include ${D}${includedir}/${BPN}
	cp -R --no-dereference --preserve=mode,links -v ${S}/backport-include ${D}${includedir}/${BPN}
	rm -f ${D}${includedir}/${BPN}/backport-include/linux/module.h
}
