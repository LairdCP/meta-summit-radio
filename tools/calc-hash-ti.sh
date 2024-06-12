#! /bin/sh

[ -z "${1}" ] && { echo "Usage: $0 <version>"; exit 1; }

ver=${1}

file="radio-stack-ti-hashes.inc"
prefix="https://files.devops.rfpros.com/builds/linux"

calc_file () {
  wget ${prefix}/${1}/${ver}/${2} || exit 1
  echo "SRC_URI[${3}.md5sum] = \"$(md5sum ${2} | awk '{print $1}')\"" >> ${file}
  echo "SRC_URI[${3}.sha256sum] = \"$(sha256sum ${2} | awk '{print $1}')\"" >> ${file}
  rm -f ${2}
}

echo "RADIO_VERSION = \"${ver}\"\n" > ${file}

calc_file "summit_supplicant/laird" "summit_supplicant-src-${ver}.tar.gz" "summit-supplicant-src"
calc_file "lrd-network-manager/src" "lrd-network-manager-src-${ver}.tar.xz" "summit-network-manager"
calc_file "backports/laird" "backports-summit-${ver}.tar.bz2" "summit-backports"

for i in WW US JP EU CA AU
do
  calc_file "firmware" "summit-ti351-${i}-firmware-${ver}.tar.bz2" "ti351-${i}-firmware"
done