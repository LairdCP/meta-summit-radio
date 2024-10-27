SECTION = "kernel"

LICENSE = "GPL-2.0-only & Ezurio"
NO_GENERIC_LICENSE[Ezurio] = "LICENSE.ezurio"
LIC_FILES_CHKSUM = "\
    file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46 \
    file://LICENSE.ezurio;md5=fd3dd0630b215465b6f50540642d5b93 \
    "

PV = "${RADIO_VERSION}"

inherit module cml1

DEPENDS += "coreutils-native bison-native flex-native kern-tools-native"

S = "${WORKDIR}/summit-backports-${PV}"

SUMMIT_URI ?= "${SUMMIT_URI_BASE}"
SUMMIT_URI:summit-internal = "${SUMMIT_URI_BASE}/backports/laird/${PV}"

SRC_URI += "\
    ${SUMMIT_URI}/summit-backports-${PV}.tar.bz2;name=summit-backports \
    file://0001-skb_pull_data-Fixed-backports-with-kernel-5.15.patch \
    file://0002-lrdmwl-Fixed-reset-gpio-operation.patch \
    file://0003-lrdmwl-fix-no-macid-can-be-allocated.patch \
    "

EXTRA_OEMAKE = "\
    KLIB=${STAGING_KERNEL_DIR} \
    KLIB_BUILD=${STAGING_KERNEL_BUILDDIR} \
    "

KERNEL_MODULE_PACKAGE_SUFFIX = "-backports-${KERNEL_VERSION}"

KCONFIG_CONFIG_COMMAND = "${EXTRA_OEMAKE} CC=${BUILD_CC} menuconfig"

# Reconfiguration must be triggered on kernel config change through
# defconfig or menuconfig, menuconfig does not have it's own stamp
# and runs after do_configure, so use compile instead
do_configure[depends] += "virtual/kernel:do_compile"

do_configure () {
    rm -f "${S}/.kernel_config_md5"

    # Copy defconfig to .config if .config does not exist. This allows
    # recipes to manage the .config themselves in do_configure:prepend().
    if [ -f "${WORKDIR}/defconfig" ] && [ ! -f "${S}/.config" ]; then
        cp "${WORKDIR}/defconfig" "${S}/.config"
    fi

    # Copy in-tree defconfig to .config if .config does not exist.
    if [ ! -f "${S}/.config" ]; then
        cp "${S}/defconfigs/${BACKPORTS_CONFIG}" "${S}/.config"
    fi

    "${S}/kconf/merge_config.sh" -O "${S}" -m "${S}/.config" ${@" ".join(find_cfgs(d))}

    unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS
    oe_runmake CC="${BUILD_CC}" olddefconfig
}

do_savedefconfig () {
    bbplain "Saving defconfig to:\n${S}/defconfig"
    unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS
    oe_runmake CC="${BUILD_CC}" savedefconfig
}

do_savedefconfig[nostamp] = "1"
addtask savedefconfig after do_configure

do_install:append () {
    install -d "${D}${includedir}/${BPN}"
    cp -R --no-dereference --preserve=mode,links -v "${S}/include" "${D}${includedir}/${BPN}"
    cp -R --no-dereference --preserve=mode,links -v "${S}/backport-include" "${D}${includedir}/${BPN}"
    rm -f "${D}${includedir}/${BPN}/backport-include/linux/module.h"
}
