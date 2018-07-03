SECTION = "kernel/userland"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://README;md5=3f01d778be8f953962388307ee38ed2b"
DEPENDS = "libnl openssl"
SUMMARY = "User space daemon for extended IEEE 802.11 management"

inherit update-rc.d systemd
INITSCRIPT_NAME = "hostapd"

SYSTEMD_SERVICE_${PN} = "hostapd.service"
SYSTEMD_AUTO_ENABLE_${PN} = "disable"

SRC_URI = " \
	https://raw.github.com/LairdCP/Release-Packages/master/ST60/5.0.0.x/${PV}/sterling_supplicant-src-${PV}.tar.bz2 \
    file://defconfig \
    file://init \
    file://hostapd.service \
"

SRC_URI[md5sum] = "533084a6688daa56004e6c5de1ac7bfa"
SRC_URI[sha256sum] = "447bc451497b5e9ebe68ff2ce7f930bcbecb8edab502de12c35bee4c4c79c3ec"

S = "${WORKDIR}/sterling_supplicant-src-${PV}/sterling_supplicant"
B = "${S}/hostapd"

do_unpack_extra() {
	cd ${WORKDIR}/sterling_supplicant-src-${PV}
	tar -xf sterling_supplicant-src.tar
}
addtask unpack_extra after do_unpack before do_patch

do_configure() {
    install -m 0644 ${WORKDIR}/defconfig ${B}/.config
}

do_compile() {
    export CFLAGS="-MMD -O2 -Wall -g -I${STAGING_INCDIR}/libnl3"
    make
}

do_install() {
    install -d ${D}${sbindir} ${D}${sysconfdir}/init.d ${D}${systemd_unitdir}/system/
    install -m 0644 ${B}/hostapd.conf ${D}${sysconfdir}
    install -m 0755 ${B}/hostapd ${D}${sbindir}
    install -m 0755 ${B}/hostapd_cli ${D}${sbindir}
    install -m 755 ${WORKDIR}/init ${D}${sysconfdir}/init.d/hostapd
    install -m 0644 ${WORKDIR}/hostapd.service ${D}${systemd_unitdir}/system/
    sed -i -e 's,@SBINDIR@,${sbindir},g' -e 's,@SYSCONFDIR@,${sysconfdir},g' ${D}${systemd_unitdir}/system/hostapd.service
}

CONFFILES_${PN} += "${sysconfdir}/hostapd.conf"
