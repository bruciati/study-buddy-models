{ pkgs ? import <nixpkgs> {} }:

let
  jdk = pkgs.jdk17_headless;
in
pkgs.mkShell {
  buildInputs = [
    jdk
    pkgs.maven
  ];
}
