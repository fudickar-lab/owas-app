# Entwurf einer interaktiven App zur Körperhaltungsanalyse unter Verwendung der OWAS-Methode
**Bachelorarbeit**

von Franziska Dang Quang  

***

## Hinweise zur Ordnerstruktur: Quellcode
Die allgemeine Ordnerstruktur ist durch Android vorgegeben. Der Quellcode befindet sich im Ordner `OWASHaltungsanalyse/app/src/`.
- `main/java/` Java-Quellcode
    - `database` Zugriff auf die Datenbank (über das Framework Room) und Datei-Export
    - `di` Module für Dependency Injection (mit dem Framework Hilt)
    - `model` Programmlogik (vgl. auch Klassendiagramm in der Arbeit)
    - `presenter` Presenter
    - `view` Android-View-Komponenten (Activities, Fragments etc.), Interaktion der Oberflächen
- `main/res/` Ressourcen (UI-Layout, Icons/Bilder, Strings, Styles)
    - `drawable/` und `mipmap/` Icons und Bilder
    - `layout/` XML-Dateien, die das UI-Layout festlegen
    - `values/` Weitere Ressourcen als XML-Dateien:
        - `analysecodelist.xml` OWAS-Codes der Aktionsklasse 4. Diese werden benötigt, um während der Beobachtung eine direkte Warnung zu geben, wenn die eingegebene Körperhaltung in Aktionsklasse 4 ist.
        - `colorlist.xml` Farben für die Diagramme in der Datenanalyse
        - `colors.xml` allgemeines Farbschema der App
        - `config.xml` Konfigurationen (Länge des Timers im Warteraum)
        - `strings.xml` Alle auf der Oberfläche angezeigten Texte (auf Deutsch)
        - `styles.xml` Das Theme der App
- `test/java/` beispielhafter Unit-Test

## How to build
Informationen zum Neukompilieren der App
- IDE: Android Studio (https://developer.android.com/studio, getestet mit Version 4.1.2).  
(Alternativ auch mit JetBrains IntelliJ IDEA und Android-Plugin möglich (https://www.jetbrains.com/de-de/idea/, getestet mit Version 2020.2.3 (Ultimate Edition)))
- Java 8 (getestet mit Version 1.8.0_242, wird mit Android Studio mitgeliefert)
- Android-SDK: wird mit Android Studio mitgeliefert
- Kompilieren der App über Gradle. Benötigte `build.gradle`-Dateien sind im Projekt enthalten.
- Testen der App über den Emulator mithilfe eines "Android Virtual Device" (mit Android Studio mitgeliefert) oder auf Smartphone (dieses wird mit USB-Kabel verbunden. Zusätzlich muss USB-Debugging aktiviert sein)

## How to install
Informationen zum Installieren der bereits kompilierten APK-Datei der App (`OWASHaltungsanalyse/app/release/OWAS-App.apk`)  
Betriebssystem des Smartphones: min. Android Version 5.0 (Lollipop)
1. Smartphone mit dem PC verbinden und APK-Datei auf das Smartphone kopieren
2. APK-Datei öffnen
3. Ggf. muss die Erlaubnis gegeben werden, eine „App aus unbekannter Quelle“ zu installieren. Die Einstellung findet sich (falls nicht automatisch geöffnet) meist unter Einstellungen => Sicherheit => Unbekannte Quellen. Möglicherweise muss beim Einschalten eine Sicherheitswarnung bestätigt werden. Aus Sicherheitsgründen sollte die Einstellung nach Installation der App zurückgesetzt werden.
4. APK-Datei erneut öffnen (falls nötig) und auf „Installieren“ klicken
5. Bei einigen Geräten wird zusätzlich noch eine Warnung „Von Play Protect blockiert“ angezeigt. Bitte hier „Trotzdem installieren“ und NICHT „OK“ wählen.
6. Die App sollte nun installiert sein. Über das Icon auf dem Home Screen mit dem Namen „OWAS-App“ kann die App gestartet werden.

## Genutzte Frameworks
- Android (https://developer.android.com/)
- Android Jetpack (https://developer.android.com/jetpack)
- Hilt (https://developer.android.com/training/dependency-injection/hilt-android)
- MPAndroidChart (https://github.com/PhilJay/MPAndroidChart)
- JUnit 4 (https://junit.org/junit4/)
- mockito (https://site.mockito.org/)