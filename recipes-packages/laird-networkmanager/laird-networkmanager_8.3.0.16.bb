SUMMARY = "Laird Connectivity NetworkManager"
SECTION = "net/misc"

LICENSE = "GPLv2+ & LGPLv2.1+"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
                    file://COPYING.LGPL;md5=4fbd65380cdd255951079008b364516c \
"

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

RDEPENDS_${PN} += "less"

inherit autotools gettext update-rc.d systemd bash-completion gtk-doc lrd-url

SRC_URI = " \
    ${LRD_60_URI_BASE}/lrd-network-manager-src-${PV}.tar.xz \
    file://networkmanager.initd \
    file://NetworkManager.conf \
    file://0001-Fixed-configure.ac-Fix-pkgconfig-sysroot-locations.patch \
    file://0002-Do-not-create-settings-settings-property-documentati.patch \
    file://0001-install-firewalld-to-var-libdir-rather-than-hardcod-.patch \
"

SRC_URI[md5sum] = "0f754a1374c75790e143f7844c5891b4"
SRC_URI[sha256sum] = "cd6081aac3006578825de4c33f6f702b3275baaff34509a853280c47e7b79a6a"

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
PACKAGECONFIG[resolvconf] = "--with-resolvconf=${base_sbindir}/resolvconf,,,resolvconf"
PACKAGECONFIG[gnutls] = "--with-crypto=gnutls,,gnutls"
PACKAGECONFIG[wifi] = "--enable-wifi=yes,--enable-wifi=no,wpa-supplicant"
PACKAGECONFIG[ifupdown] = "--enable-ifupdown,--disable-ifupdown"
PACKAGECONFIG[qt4-x11-free] = "--enable-qt,--disable-qt,qt4-x11-free"
PACKAGECONFIG[cloud-setup] = "--with-nm-cloud-setup=yes,--with-nm-cloud-setup=no"

PACKAGES =+ " \
  ${PN}-nmtui ${PN}-nmtui-doc \
  ${PN}-adsl ${PN}-cloud-setup \
"

FILES_${PN}-adsl = "${libdir}/NetworkManager/libnm-device-plugin-adsl.so"

FILES_${PN}-cloud-setup = " \
    ${libexecdir}/nm-cloud-setup \
    ${systemd_system_unitdir}/nm-cloud-setup.service \
    ${systemd_system_unitdir}/nm-cloud-setup.timer \
    ${libdir}/NetworkManager/dispatcher.d/90-nm-cloud-setup.sh \
    ${libdir}/NetworkManager/dispatcher.d/no-wait.d/90-nm-cloud-setup.sh \
"
ALLOW_EMPTY_${PN}-cloud-setup = "1"
SYSTEMD_SERVICE_${PN}-cloud-setup = "${@bb.utils.contains('PACKAGECONFIG', 'cloud-setup', 'nm-cloud-setup.service nm-cloud-setup.timer', '', d)}"

FILES_${PN} += " \
    ${libexecdir} \
    ${libdir}/NetworkManager/*/*.so \
    ${libdir}/NetworkManager \
    ${libdir}/firewalld/zones \
    ${nonarch_libdir}/NetworkManager/conf.d \
    ${nonarch_libdir}/NetworkManager/dispatcher.d \
    ${nonarch_libdir}/NetworkManager/dispatcher.d/pre-down.d \
    ${nonarch_libdir}/NetworkManager/dispatcher.d/pre-up.d \
    ${nonarch_libdir}/NetworkManager/dispatcher.d/no-wait.d \
    ${nonarch_libdir}/NetworkManager/VPN \
    ${nonarch_libdir}/NetworkManager/system-connections \
    ${datadir}/polkit-1 \
    ${datadir}/dbus-1 \
    ${nonarch_base_libdir}/udev/* \
    ${systemd_system_unitdir} \
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

ALTERNATIVE_PRIORITY = "100"
ALTERNATIVE_${PN} = "${@bb.utils.contains('DISTRO_FEATURES','systemd','resolv-conf','',d)}"
ALTERNATIVE_TARGET[resolv-conf] = "${@bb.utils.contains('DISTRO_FEATURES','systemd','${sysconfdir}/resolv-conf.NetworkManager','',d)}"
ALTERNATIVE_LINK_NAME[resolv-conf] = "${@bb.utils.contains('DISTRO_FEATURES','systemd','${sysconfdir}/resolv.conf','',d)}"

do_install_append() {
    install -Dm 0644 ${WORKDIR}/NetworkManager.conf ${D}${sysconfdir}/NetworkManager/NetworkManager.conf
    install -Dm 0755 ${WORKDIR}/networkmanager.initd ${D}${sysconfdir}/init.d/network-manager

    rm -rf ${D}/run ${D}${localstatedir}/run

    if ${@bb.utils.contains('DISTRO_FEATURES','systemd','true','false',d)}; then
        # For read-only filesystem, do not create links during bootup
        ln -sf ../run/NetworkManager/resolv.conf ${D}${sysconfdir}/resolv-conf.NetworkManager

        # systemd v210 and newer do not need this rule file
        rm ${D}/${nonarch_base_libdir}/udev/rules.d/84-nm-drivers.rules
    fi
}
