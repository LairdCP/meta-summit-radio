
DEFCONFIG = "config_openssl"

require summit-supplicant.inc radio-stack-lwb-version.inc

DEPENDS += "dbus"
RRECOMMENDS_${PN} += "summit-supplicant-libs-lwb"

FILES_${PN} += "${datadir}/dbus-1/system-services/* ${sysconfdir}/dbus-1/system.d/*"

do_install_append() {
	install -D -t ${D}${libdir} -m 0644 wpa_supplicant/libwpa_client.so
	install -D -t ${D}${sysconfdir}/dbus-1/system.d -m 644 wpa_supplicant/dbus/dbus-wpa_supplicant.conf
	install -D -t ${D}${datadir}/dbus-1/system-services -m 644 wpa_supplicant/dbus/*.service
}
