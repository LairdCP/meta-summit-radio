SUMMARY = "Laird NetworkManager"
SECTION = "net/misc"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=cbbffd568227ada506640fe950a4823b"

DEPENDS = " \
    intltool-native \
    libxslt-native \
    glib-2.0-native \
    libnl \
    libgudev \
    util-linux \
    libndp \
    libnewt \
    curl \
"
inherit autotools gettext update-rc.d systemd bash-completion gtk-doc lrd-url

SRC_URI = " \
    ${LRD_60_URI_BASE}/lrd-network-manager-src-${PV}.tar.xz \
    file://networkmanager.initd \
    file://NetworkManager.conf \
"
SRC_URI[md5sum] = "e0301339c83442e9ade379005964cd77"
SRC_URI[sha256sum] = "90a537e02624792265e54a4ed1ee6c583cc7ed33aed165090ca7fbca9b526fe9"

S = "${WORKDIR}/lrd-network-manager-${PV}"

EXTRA_OECONF = " \
    --disable-ifcfg-rh \
    --disable-more-warnings \
    --with-iptables=${sbindir}/iptables \
    --disable-tests \
    --with-udev-dir=${nonarch_base_libdir}/udev \
    --disable-ovs \
    --disable-introspection \
    --disable-nls \
    --disable-vala \
    --disable-gtk-doc \
    --disable-gtk-doc-html \
    --disable-gtk-doc-pdf \
"

do_compile_prepend() {
        export GIR_EXTRA_LIBS_PATH="${B}/libnm/.libs:${B}/libnm-glib/.libs:${B}/libnm-util/.libs"
}

PACKAGECONFIG ??= "nss ifupdown dnsmasq \
    ${@bb.utils.contains('DISTRO_FEATURES','systemd','systemd','consolekit',d)} \
    ${@bb.utils.contains('DISTRO_FEATURES','bluetooth','bluez5','',d)} \
    ${@bb.utils.contains('DISTRO_FEATURES','wifi','wifi','',d)} \
    ${@bb.utils.contains('DISTRO_FEATURES','polkit','polkit','',d)} \
"
PACKAGECONFIG[systemd] = " \
    --with-systemdsystemunitdir=${systemd_unitdir}/system --with-session-tracking=systemd, \
    --without-systemdsystemunitdir, \
"
PACKAGECONFIG[polkit] = "--enable-polkit --enable-polkit-agent,--disable-polkit --disable-polkit-agent,polkit"
PACKAGECONFIG[bluez5] = "--enable-bluez5-dun,--disable-bluez5-dun,bluez5"
# consolekit is not picked by shlibs, so add it to RDEPENDS too
PACKAGECONFIG[consolekit] = "--with-session-tracking=consolekit,,consolekit,consolekit"
PACKAGECONFIG[modemmanager] = "--with-modem-manager-1,--without-modem-manager-1,modemmanager"
PACKAGECONFIG[ofono] = "--with-ofono,--without-ofono,ofono"
PACKAGECONFIG[ppp] = "--enable-ppp,--disable-ppp,ppp,ppp"
# Use full featured dhcp client instead of internal one
PACKAGECONFIG[dhclient] = "--with-dhclient=${base_sbindir}/dhclient,,,dhcp-client"
PACKAGECONFIG[dhcpcd] = "--with-dhcpcd=${base_sbindir}/dhcpcd,,,dhcpcd"
PACKAGECONFIG[dnsmasq] = "--with-dnsmasq=${bindir}/dnsmasq"
PACKAGECONFIG[nss] = "--with-crypto=nss,,nss"
PACKAGECONFIG[glib] = "--with-libnm-glib,,dbus-glib-native dbus-glib"
PACKAGECONFIG[resolvconf] = "--with-resolvconf=${base_sbindir}/resolvconf,,,resolvconf"
PACKAGECONFIG[gnutls] = "--with-crypto=gnutls,,gnutls"
PACKAGECONFIG[wifi] = "--enable-wifi=yes,--enable-wifi=no,wpa-supplicant"
PACKAGECONFIG[ifupdown] = "--enable-ifupdown,--disable-ifupdown"
PACKAGECONFIG[qt4-x11-free] = "--enable-qt,--disable-qt,qt4-x11-free"

PACKAGES =+ "libnmutil libnmglib libnmglib-vpn \
  ${PN}-nmtui ${PN}-nmtui-doc \
  ${PN}-adsl \
"

FILES_libnmutil += "${libdir}/libnm-util.so.*"
FILES_libnmglib += "${libdir}/libnm-glib.so.*"
FILES_libnmglib-vpn += "${libdir}/libnm-glib-vpn.so.*"

FILES_${PN}-adsl = "${libdir}/NetworkManager/libnm-device-plugin-adsl.so"

FILES_${PN} += " \
    ${libexecdir} \
    ${libdir}/NetworkManager/*/*.so \
    ${nonarch_libdir}/NetworkManager/VPN \
    ${nonarch_libdir}/NetworkManager/conf.d \
    ${nonarch_libdir}/NetworkManager/dispatcher.d \
    ${nonarch_libdir}/NetworkManager/system-connections \
    ${datadir}/polkit-1 \
    ${datadir}/dbus-1 \
    ${noarch_base_libdir}/udev/* \
    ${systemd_unitdir}/system \
    ${libdir}/pppd \
"
RRECOMMENDS_${PN} += "iptables \
    ${@bb.utils.contains('PACKAGECONFIG','dnsmasq','dnsmasq','',d)} \
"

RCONFLICTS_${PN} = "connman networkmanager"
RREPLACES_${PN} = "networkmanager"

FILES_${PN}-dev += " \
    ${datadir}/NetworkManager/gdb-cmd \
    ${libdir}/pppd/*/*.la \
    ${libdir}/NetworkManager/*.la \
    ${libdir}/NetworkManager/*/*.la \
    ${datadir}/gir-1.0 \
"

FILES_${PN}-nmtui = " \
    ${bindir}/nmtui \
    ${bindir}/nmtui-edit \
    ${bindir}/nmtui-connect \
    ${bindir}/nmtui-hostname \
"

FILES_${PN}-nmtui-doc = " \
    ${mandir}/man1/nmtui* \
"

INITSCRIPT_NAME = "network-manager"
SYSTEMD_SERVICE_${PN} = "${@bb.utils.contains('PACKAGECONFIG', 'systemd', 'NetworkManager.service NetworkManager-dispatcher.service', '', d)}"

do_install_append() {
    install -Dm 0644 ${WORKDIR}/NetworkManager.conf ${D}${sysconfdir}/NetworkManager/NetworkManager.conf
    install -Dm 0755 ${WORKDIR}/networkmanager.initd ${D}${sysconfdir}/init.d/network-manager
    rm -rf ${D}/run ${D}${localstatedir}/run
}
