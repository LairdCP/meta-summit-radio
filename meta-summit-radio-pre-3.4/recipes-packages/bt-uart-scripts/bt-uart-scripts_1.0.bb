SUMMARY = "Summit 60 series Bluetooth UART Startup scripts"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit allarch systemd

BT_SERIAL_PORT ?= "/dev/ttyS1"

SRC_URI = " \
    file://bt-service.sh \
    file://80-btattach.rules-sysv \
    file://80-btattach.rules-sysd \
    file://btattach.service \
    "

S = "${WORKDIR}"

FILES_${PN} += "${systemd_unitdir}/system ${sysconfdir}"

SYSTEMD_SERVICE_${PN} = "btattach.service"

do_install() {
    install -D -m 0775 ${S}/bt-service.sh ${D}${bindir}/bt-service.sh

    install -d ${D}${sysconfdir}/default
    echo "PORT=${BT_SERIAL_PORT}" > ${D}${sysconfdir}/default/bt-service

    if ${@bb.utils.contains('DISTRO_FEATURES','systemd','true','false',d)}; then
        install -D -m 0644 ${S}/80-btattach.rules-sysd \
            ${D}${sysconfdir}/udev/rules.d/80-btattach.rules
        install -D -m 0644 ${S}/btattach.service \
            ${D}${systemd_unitdir}/system/btattach.service
    else
        install -D -m 0644 ${S}/80-btattach.rules-sysv \
            ${D}${sysconfdir}/udev/rules.d/80-btattach.rules
    fi
}
