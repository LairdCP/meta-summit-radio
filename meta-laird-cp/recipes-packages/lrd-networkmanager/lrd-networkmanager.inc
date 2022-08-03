SUMMARY = "Laird Connectivity NetworkManager"
SECTION = "net/misc"

LICENSE = "GPLv2+ & LGPLv2.1+"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
                    file://COPYING.LGPL;md5=4fbd65380cdd255951079008b364516c \
"

DEPENDS = " \
    intltool-native \
    libnl \
    udev \
    util-linux \
    libndp \
    libnewt \
"

inherit autotools gettext update-rc.d systemd bash-completion update-alternatives

LRD_URI ?= "${LRD_URI_BASE}"
LRD_URI:laird-internal = "https://files.devops.rfpros.com/builds/linux/lrd-network-manager/src/${PV}"

SRC_URI = " \
    ${LRD_URI}/lrd-network-manager-src-${PV}.tar.xz;name=lrd-network-manager \
    file://networkmanager.initd \
    file://NetworkManager.conf \
    file://0001-Fixed-configure.ac-Fix-pkgconfig-sysroot-locations.patch \
    file://0002-Do-not-create-settings-settings-property-documentati.patch \
    file://0003-install-firewalld-to-var-libdir-rather-than-hardcod-.patch \
"

S = "${WORKDIR}/lrd-network-manager-${PV}"

EXTRA_OECONF = " \
    --disable-ifcfg-rh \
    --disable-more-warnings \
    --with-iptables=${sbindir}/iptables \
    --disable-tests \
    --with-nmtui=yes \
    --with-udev-dir=${nonarch_base_libdir}/udev \
    --with-dhclient=no \
    --with-dhcpcd=no \
    --with-dhcpcanon=no \
    --with-netconfig=no \
    --disable-introspection \
    --disable-concheck \
    --disable-nls \
    --disable-vala \
    --disable-gtk-doc \
    --disable-gtk-doc-html \
    --disable-gtk-doc-pdf \
"

# stolen from https://github.com/void-linux/void-packages/blob/master/srcpkgs/NetworkManager/template
# avoids:
# | ../NetworkManager-1.16.0/libnm-core/nm-json.c:106:50: error: 'RTLD_DEEPBIND' undeclared (first use in this function); did you mean 'RTLD_DEFAULT'?
CFLAGS:append_libc-musl = " \
    -DRTLD_DEEPBIND=0 \
"

do_compile:prepend() {
    export GIR_EXTRA_LIBS_PATH="${B}/src/libnm-client-impl/.libs"
}

PACKAGECONFIG ?= "gnutls dnsmasq nmcli \
    ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'systemd', bb.utils.contains('DISTRO_FEATURES', 'x11', 'consolekit', '', d), d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'bluetooth', 'bluez5', '', d)} \
    ${@bb.utils.filter('DISTRO_FEATURES', 'wifi polkit', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'selinux', 'selinux audit', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES','3g','modemmanager','',d)} \
"

inherit ${@bb.utils.contains('PACKAGECONFIG', 'nmcli', 'bash-completion', '', d)}

PACKAGECONFIG[systemd] = " \
    --with-systemdsystemunitdir=${systemd_unitdir}/system --with-session-tracking=systemd, \
    --without-systemdsystemunitdir, \
"
PACKAGECONFIG[polkit] = "--enable-polkit,--disable-polkit,polkit"
PACKAGECONFIG[bluez5] = "--enable-bluez5-dun,--disable-bluez5-dun,bluez5"
# consolekit is not picked by shlibs, so add it to RDEPENDS too
PACKAGECONFIG[consolekit] = "--with-session-tracking=consolekit,,consolekit,consolekit"
PACKAGECONFIG[modemmanager] = "--with-modem-manager-1=yes,--with-modem-manager-1=no,modemmanager"
PACKAGECONFIG[ofono] = "--with-ofono,--without-ofono,ofono"
PACKAGECONFIG[ppp] = "--enable-ppp,--disable-ppp,ppp,ppp"
PACKAGECONFIG[dnsmasq] = "--with-dnsmasq=${bindir}/dnsmasq,,,dnsmasq"
PACKAGECONFIG[nss] = "--with-crypto=nss,,nss"
PACKAGECONFIG[resolvconf] = "--with-resolvconf=${base_sbindir}/resolvconf,,,resolvconf"
PACKAGECONFIG[gnutls] = "--with-crypto=gnutls,,gnutls"
PACKAGECONFIG[wifi] = "--with-wext=no --enable-wifi=yes,--with-wext=no --enable-wifi=no,wpa-supplicant"
PACKAGECONFIG[ifupdown] = "--enable-ifupdown,--disable-ifupdown"
PACKAGECONFIG[qt4-x11-free] = "--enable-qt,--disable-qt,qt4-x11-free"
PACKAGECONFIG[cloud-setup] = "--with-nm-cloud-setup=yes,--with-nm-cloud-setup=no"
PACKAGECONFIG[nmcli] = "--with-nmcli=yes,--with-nmcli=no,readline"
PACKAGECONFIG[ovs] = "--enable-ovs,--disable-ovs,jansson"
PACKAGECONFIG[audit] = "--with-libaudit,--without-libaudit,audit"
PACKAGECONFIG[selinux] = "--with-selinux,--without-selinux,libselinux"

PACKAGES =+ " \
  ${PN}-nmcli ${PN}-nmcli-doc \
  ${PN}-nmtui ${PN}-nmtui-doc \
  ${PN}-adsl ${PN}-cloud-setup \
"

SYSTEMD_PACKAGES = "${PN} ${PN}-cloud-setup"

FILES:${PN}-adsl = "${libdir}/NetworkManager/*/libnm-device-plugin-adsl.so"

FILES:${PN}-cloud-setup = " \
    ${libexecdir}/nm-cloud-setup \
    ${systemd_system_unitdir}/nm-cloud-setup.service \
    ${systemd_system_unitdir}/nm-cloud-setup.timer \
    ${libdir}/NetworkManager/dispatcher.d/90-nm-cloud-setup.sh \
    ${libdir}/NetworkManager/dispatcher.d/no-wait.d/90-nm-cloud-setup.sh \
"
ALLOW_EMPTY:${PN}-cloud-setup = "1"
SYSTEMD_SERVICE:${PN}-cloud-setup = "${@bb.utils.contains('PACKAGECONFIG', 'cloud-setup', 'nm-cloud-setup.service nm-cloud-setup.timer', '', d)}"

FILES:${PN} += " \
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

RDEPENDS:${PN} += "ethtool"
RRECOMMENDS:${PN} += "iptables \
    ${@bb.utils.filter('PACKAGECONFIG', 'dnsmasq', d)} \
"
RCONFLICTS:${PN} = "connman networkmanager"
RREPLACES:${PN} = "networkmanager"

FILES:${PN}-dev += " \
    ${datadir}/NetworkManager/gdb-cmd \
    ${libdir}/pppd/*/*.la \
    ${libdir}/NetworkManager/*.la \
    ${libdir}/NetworkManager/*/*.la \
"

RRECOMMENDS:${PN}-nmcli += "less"

FILES:${PN}-nmcli = " \
    ${bindir}/nmcli \
"

FILES:${PN}-nmcli-doc = " \
    ${mandir}/man1/nmcli* \
"

FILES:${PN}-nmtui = " \
    ${bindir}/nmtui \
    ${bindir}/nmtui-edit \
    ${bindir}/nmtui-connect \
    ${bindir}/nmtui-hostname \
"

FILES:${PN}-nmtui-doc = " \
    ${mandir}/man1/nmtui* \
"

INITSCRIPT_NAME = "network-manager"
SYSTEMD_SERVICE:${PN} = "${@bb.utils.contains('PACKAGECONFIG', 'systemd', 'NetworkManager.service NetworkManager-dispatcher.service', '', d)}"

ALTERNATIVE_PRIORITY = "100"
ALTERNATIVE:${PN} = "${@bb.utils.contains('DISTRO_FEATURES','systemd','resolv-conf','',d)}"
ALTERNATIVE_TARGET[resolv-conf] = "${@bb.utils.contains('DISTRO_FEATURES','systemd','${sysconfdir}/resolv-conf.NetworkManager','',d)}"
ALTERNATIVE_LINK_NAME[resolv-conf] = "${@bb.utils.contains('DISTRO_FEATURES','systemd','${sysconfdir}/resolv.conf','',d)}"

do_install:append() {
    install -Dm 0644 ${WORKDIR}/NetworkManager.conf ${D}${sysconfdir}/NetworkManager/NetworkManager.conf

    rm -rf ${D}/run ${D}${localstatedir}/run

    if ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'true', 'false', d)}; then
        # For read-only filesystem, do not create links during bootup
        ln -sf ../run/NetworkManager/resolv.conf ${D}${sysconfdir}/resolv-conf.NetworkManager

        # systemd v210 and newer do not need this rule file
        rm ${D}/${nonarch_base_libdir}/udev/rules.d/84-nm-drivers.rules
    else
        install -Dm 0755 ${WORKDIR}/networkmanager.initd ${D}${sysconfdir}/init.d/network-manager
    fi
}

RPROVIDES:${PN} += "networkmanager"
RREPLACES:${PN} += "networkmanager"
RCONFLICTS:${PN} += "networkmanager"

RPROVIDES:${PN}-nmcli += "networkmanager-nmcli"
RREPLACES:${PN}-nmcli += "networkmanager-nmcli"
RCONFLICTS:${PN}-nmcli += "networkmanager-nmcli"

RPROVIDES:${PN}-nmcli-doc += "networkmanager-nmcli-doc"
RREPLACES:${PN}-nmcli-doc += "networkmanager-nmcli-doc"
RCONFLICTS:${PN}-nmcli-doc += "networkmanager-nmcli-doc"

RPROVIDES:${PN}-nmtui += "networkmanager-nmtui"
RREPLACES:${PN}-nmtui += "networkmanager-nmtui"
RCONFLICTS:${PN}-nmtui += "networkmanager-nmtui"

RPROVIDES:${PN}-nmtui-doc += "networkmanager-nmtui-doc"
RREPLACES:${PN}-nmtui-doc += "networkmanager-nmtui-doc"
RCONFLICTS:${PN}-nmtui-doc += "networkmanager-nmtui-doc"
