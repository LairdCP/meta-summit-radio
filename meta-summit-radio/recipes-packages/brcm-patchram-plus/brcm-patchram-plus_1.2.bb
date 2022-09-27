SUMMARY = "Infineon/Cypress/Broadcom Patchram Plus utility"
SECTION = "Wireless"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=691691b063f1b4034300dc452e36b68d"

inherit pkgconfig

SRC_URI = "git://github.com/LairdCP/brcm_patchram.git;protocol=https"

SRCREV = "241e4896e2f2ee77883bdd6f1e404bf9e9df52f7"

S = "${WORKDIR}/git"

DEPENDS += "${@bb.utils.contains('DISTRO_FEATURES','bluez5','bluez5','bluez4',d)}"

RRECOMMENDS:${PN} = "kernel-module-lwb-backports-summit"

do_compile () {
	oe_runmake brcm_patchram_plus
}

do_install () {
	install -d ${D}${sbindir}
	install -m 755 brcm_patchram_plus ${D}${sbindir}
}
