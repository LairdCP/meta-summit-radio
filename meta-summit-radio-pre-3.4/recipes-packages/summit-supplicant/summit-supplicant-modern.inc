
DEFCONFIG = "config_openssl"

require summit-supplicant.inc

DEPENDS += "dbus"

FILES_${PN} += "${datadir}/dbus-1/system-services/* ${sysconfdir}/dbus-1/system.d/*"

do_install_append() {
	install -m 0644 -D -t ${D}${libdir} libwpa_client.so
	install -m 0644 -D -t ${D}${sysconfdir}/dbus-1/system.d dbus/dbus-wpa_supplicant.conf
	install -m 0644 -D -t ${D}${datadir}/dbus-1/system-services dbus/*.service
}
