SUMMARY = "NetworkManager"
SECTION = "net/misc"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=cbbffd568227ada506640fe950a4823b \
                    file://libnm-util/COPYING;md5=1c4fa765d6eb3cd2fbd84344a1b816cd \
"

inherit autotools gettext systemd bluetooth bash-completion vala lrd-url

SRC_URI += "${LRD_URI_BASE}/lrd-network-manager-src-${PV}.tar.xz"

SRC_URI[md5sum] = "dcafe0b89d2685706a24634e48aaa7ff"
SRC_URI[sha256sum] = "4364e68a5ef35c55784b78956bce5b7aa23ec02a2e5c700617cd5f6faed78b50"

DEPENDS = "libnl dbus dbus-glib libgudev util-linux libndp libnewt intltool-native curl"

S = "${WORKDIR}/lrd-network-manager-${PV}"

EXTRA_OECONF = " \
    --disable-ovs \
    --disable-ifcfg-rh \
    --disable-ifupdown \
    --disable-more-warnings \
    --with-iptables=${sbindir}/iptables \
    --disable-tests \
    --disable-more-warnings \
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

PACKAGECONFIG ??= "nss ifupdown dhclient dnsmasq \
    ${@bb.utils.contains('DISTRO_FEATURES','systemd','systemd','consolekit',d)} \
    ${@bb.utils.contains('DISTRO_FEATURES','bluetooth','${BLUEZ}','',d)} \
    ${@bb.utils.contains('DISTRO_FEATURES','wifi','wifi','',d)} \
"
PACKAGECONFIG[systemd] = " \
    --with-systemdsystemunitdir=${systemd_unitdir}/system --with-session-tracking=systemd --enable-polkit, \
    --without-systemdsystemunitdir, \
    polkit \
"
PACKAGECONFIG[bluez5] = "--enable-bluez5-dun,--disable-bluez5-dun,bluez5"
# consolekit is not picked by shlibs, so add it to RDEPENDS too
PACKAGECONFIG[consolekit] = "--with-session-tracking=consolekit,,consolekit,consolekit"
PACKAGECONFIG[modemmanager] = "--with-modem-manager-1=yes,--with-modem-manager-1=no,modemmanager"
PACKAGECONFIG[ppp] = "--enable-ppp,--disable-ppp,ppp,ppp"
# Use full featured dhcp client instead of internal one
PACKAGECONFIG[dhclient] = "--with-dhclient=${base_sbindir}/dhclient,,,dhcp-client"
PACKAGECONFIG[dhcpcd] = "--with-dhcpcd=${base_sbindir}/dhcpcd,,,dhcpcd"
PACKAGECONFIG[dnsmasq] = "--with-dnsmasq=${bindir}/dnsmasq"
PACKAGECONFIG[nss] = "--with-crypto=nss,,nss"
PACKAGECONFIG[gnutls] = "--with-crypto=gnutls,,gnutls libgcrypt"
PACKAGECONFIG[wifi] = "--enable-wifi=yes,--enable-wifi=no,,wpa-supplicant"
PACKAGECONFIG[ifupdown] = "--enable-ifupdown,--disable-ifupdown"
PACKAGECONFIG[netconfig] = "--with-netconfig=yes,--with-netconfig=no"
PACKAGECONFIG[qt4-x11-free] = "--enable-qt,--disable-qt,qt4-x11-free"

PACKAGES =+ "libnmutil libnmglib libnmglib-vpn ${PN}-tests \
  ${PN}-nmtui ${PN}-nmtui-doc \
  ${PN}-adsl \
"

FILES_libnmutil += "${libdir}/*/libnm-util.so.*"
FILES_libnmglib += "${libdir}/*/libnm-glib.so.*"
FILES_libnmglib-vpn += "${libdir}/*/libnm-glib-vpn.so.*"

FILES_${PN}-adsl = "${libdir}/NetworkManager/*/libnm-device-plugin-adsl.so"

FILES_${PN} += " \
    ${libexecdir} \
    ${libdir}/pppd/*/nm-pppd-plugin.so \
    ${libdir}/NetworkManager/*/*.so \
    ${nonarch_libdir}/NetworkManager/VPN \
    ${nonarch_libdir}/NetworkManager/conf.d \
    ${datadir}/polkit-1 \
    ${datadir}/dbus-1 \
    ${base_libdir}/udev/* \
    ${systemd_unitdir}/system \
"
RRECOMMENDS_${PN} += "iptables \
    ${@bb.utils.contains('PACKAGECONFIG','dnsmasq','dnsmasq','',d)} \
"

RCONFLICTS_${PN} = "connman networkmanager"
RREPLACES_${PN} = "networkmanager"

FILES_${PN}-dbg += " \
    ${libdir}/NetworkManager/.debug/ \
    ${libdir}/pppd/*/.debug/ \
"

FILES_${PN}-dev += " \
    ${datadir}/NetworkManager/gdb-cmd \
    ${libdir}/pppd/*/*.la \
    ${libdir}/NetworkManager/*/*.la \
"

FILES_${PN}-tests = " \
    ${bindir}/nm-online \
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

SYSTEMD_SERVICE_${PN} = "NetworkManager.service NetworkManager-dispatcher.service"

ALTERNATIVE_PRIORITY = "100"
ALTERNATIVE_${PN} = "${@bb.utils.contains('DISTRO_FEATURES','systemd','resolv-conf','',d)}"
ALTERNATIVE_TARGET[resolv-conf] = "${@bb.utils.contains('DISTRO_FEATURES','systemd','${sysconfdir}/resolv-conf.NetworkManager','',d)}"
ALTERNATIVE_LINK_NAME[resolv-conf] = "${@bb.utils.contains('DISTRO_FEATURES','systemd','${sysconfdir}/resolv.conf','',d)}"

do_install_append() {
    rm -rf ${D}/run ${D}${localstatedir}/run

    # For read-only filesystem, do not create links during bootup
    if ${@bb.utils.contains('DISTRO_FEATURES','systemd','true','false',d)}; then
        ln -sf ../run/NetworkManager/resolv.conf ${D}${sysconfdir}/resolv-conf.NetworkManager
    fi
}
