package com.dinodevs.pacecalendarwidget;
import java.util.Arrays;

/**
 * Created by GreatApo on 08/04/2018.
 */

public class APtranslations {
    public static class translations {

        public static String[] codes = {
                "en", "zh", "hr", "cz", "nl", "fr", "de", "gr", "he", "hu", "it", "ja", "kr", "pl", "pt", "ru", "sk", "es", "th", "tr",
        };

        public static String[] languages = {
                "English", "中文", "Hrvatski", "Czech", "Nederlands", "Français", "Deutsch", "Ελληνικά", "עברית", "Magyar", "Italiano", "日本語", "한국어", "Polski", "Português", "Русский", "Slovenčina", "Español", "ไทย", "Türkçe",
        };

        public static String[] languages_en = {
                "English", "Chinese", "Croatian", "Czech", "Dutch", "French", "German", "Greek", "Hebrew", "Hungarian", "Italian", "Japanese", "Korean", "Polish", "Portuguese", "Russian", "Slovak", "Spanish", "Thai", "Turkish",
        };

        public static String[][] days = {
                {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"},
                {"星期天", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"},
                {"Nedjelja", "Ponedjeljak", "Utorak", "Srijeda", "Četvrtak", "Petak", "Subota"},
                {"Neděle","Pondělí", "Úterý", "Středa", "Čtvrtek", "Pátek", "Sobota"},
                {"Zondag", "Maandag", "Dinsdag", "Woensdag", "Donderdag", "Vrijdag", "Zaterdag"},
                {"Dimanche", "Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi"},
                {"Sonntag", "Montag", "Dienstag", "Mittwoch", "Donnerstag", "Freitag", "Samstag"},
                {"Κυριακή", "Δευτέρα", "Τρίτη", "Τετάρτη", "Πέμπτη", "Παρασκευή", "Σάββατο"},
                {"ש'","ו'","ה'","ד'","ג'","ב'","א'"},
                {"Vasárnap", "Hétfő", "Kedd", "Szerda", "Csütörtök", "Péntek", "Szombaton"},
                {"Domenica", "Lunedì", "Martedì", "Mercoledì", "Giovedì", "Venerdì", "Sabato"},
                {"日曜日", "月曜日", "火曜日", "水曜日", "木曜日", "金曜日", "土曜日"},
                {"일", "월", "화", "수", "목", "금", "토"},
                {"Niedziela", "Poniedziałek", "Wtorek", "Środa", "Czwartek", "Piątek", "Sobota"},
                {"Domingo", "Segunda", "Terça", "Quarta", "Quinta", "Sexta", "Sábado"},
                {"воскресенье", "понедельник", "вторник", "среда", "четверг", "пятница", "суббота"},
                {"Nedeľa", "Pondelok", "Utorok", "Streda", "Štvrtok", "Piatok", "Sobota"},
                {"Domingo", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado"},
                {"อาทิตย์", "จันทร์", "อังคาร", "พุธ", "พฤหัสบดี", "ุกร์", "สาร์"},
                {"Pazar", "Pazartesi", "Salı", "Çarşamba", "Perşembe", "Cuma", "Cumartesi"},
        };

        public static String[][] months = {
                {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"},
                {"一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"},
                {"Siječanj", "Veljača", "Ožujak", "Travanj", "Svibanj", "Lipanj", "Srpanj", "Kolovoz", "Rujan", "Listopad", "Studeni", "Prosinac"},
                {"Leden", "Únor", "Březen", "Duben", "Květen", "Červen", "Červenec", "Srpen", "Září", "Říjen", "Listopad", "Prosinec"},
                {"Januari", "Februari", "Maart", "April", "Mei", "Juni", "Juli", "Augustus", "September", "Oktober", "November", "December"},
                {"Janvier", "Février", "Mars", "Avril", "Mai", "Juin", "Juillet", "Août", "Septembre", "octobre", "Novembre", "Décembre"},
                {"Januar", "Februar", "März", "April", "Mai", "Juni", "Juli", "August", "September", "Oktober", "November", "Dezember"},
                {"Ιανουάριος", "Φεβρουάριος", "Μάρτιος", "Απρίλιος", "Μάιος", "Ιούνιος", "Ιούλιος", "Αύγουστος", "Σεπτέμβριος", "Οκτώβριος", "Νοέμβριος", "Δεκέμβριος"},
                {"ינואר", "פברואר", "מרץ", "אפריל", "מאי", "יוני", "יולי", "אוגוסט", "ספטמבר", "אוקטובר", "נובמבר", "דצמבר"},
                {"Január", "Február", "Március", "Április", "Május", "Június", "Július", "Augusztus", "Szeptember", "Október", "November", "December"},
                {"Gennaio", "Febbraio", "Marzo", "Aprile", "Maggio", "Giugno", "Luglio", "Agosto", "Settembre", "Ottobre", "Novembre", "Dicembre"},
                {"1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月"},
                {"1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"},
                {"Styczeń", "Luty", "Marzec", "Kwiecień", "Maj", "Czerwiec", "Lipiec", "Sierpień", "Wrzesień", "Październik", "Listopad", "Grudzień"},
                {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"},
                {"январь", "февраль", "март", "апрель", "май", "июнь", "июль", "август", "сентябрь", "октябрь", "ноябрь", "декабрь"},
                {"Január", "Február", "Marec", "Apríl", "Máj", "Jún", "Júl", "August", "September", "Október", "November", "December"},
                {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"},
                {"มกราคม", "กุมภาพันธ์", "ีนาคม", "เมษายน", "พฤษภาคม", "มิถุนายน", "กรกฎาคม", "สิงหาคม", "กันยายน", "ตุลาคม", "พฤศจิกายน", "ธันวาคม"},
                {"Ocak", "Şubat", "Mart", "Nisan", "Mayıs", "Haziran", "Temmuz", "Ağustos", "Eylül", "Ekim", "Kasım", "Aralık"},
        };

        public static String[][] other = {
                {"Select color","Show year","Monday 1st", "Vibrate", "Weeks No"},                  //"English",
                {"选择颜色","显示年份","星期一放1", "Vibrate", "Weeks No"},                           //"Chinese",
                {"Odaberite boju","Prikaži godinu","Od ponedjeljka", "Vibriraj", "Br tjedna"},     //Croatian,
                {"Vyberte barvu", "Zobrazit rok", "Od pondělka", "Vibrate", "Weeks No"},           //"Czech",
                {"Selecteer kleur","Toon jaar","Maandag 1e", "Vibrate", "Weeks No"},               //"Dutch"
                {"Choisissez la couleur","Afficher l'année","Mettez 1er", "Vibrate", "Weeks No"},  //"French",
                {"Farbe auswählen","Jahr anzeigen","Montag erste", "Vibrate", "Weeks No"},         //"German",
                {"Επιλογή χρώματος","Εμφάνιση χρονιάς","Δευτέρα 1η", "Δόνηση", "Αρ εβδομάδας"},    //"Greek",
                {"בחירת צבע","הצג שנה","החל מיום שני", "Vibrate", "Weeks No"},                     //"Hebrew"
                {"Válasszon színt","Az év megjelenítése","Hétfő 1", "Vibrate", "Weeks No"},        //"Hungarian",
                {"Seleziona colore","Mostra l'anno","Lunedì 1°", "Vibrate", "Weeks No"},           //"Italian",
                {"色を選択","年を表示","月曜日に入れて", "Vibrate", "Weeks No"},                       //"Japanese",
                {"색상 선택","연도 표시","색상 선택", "진동", "주차 표시"},                             //"Korean", by eastway11
                {"Wybierz kolor","Pokaż rok","Poniedziałek 1.", "Vibrate", "Weeks No"},            //"Polish",
                {"Selecione a cor","Mostrar ano","Segunda-feira", "Vibrate", "Weeks No"},          //"Portuguese",
                {"Выбор цвета","Показ года","Пон. 1-й", "Vibrate", "Weeks No"},                    //"Russian",
                {"Vyberte farbu","Zobraziť rok","Pondelok 1.", "Vibrate", "Weeks No"},             //"Slovak",
                {"Seleccionar el color","Mostrar año","Lunes 1°", "Vibrate", "Weeks No"},          //"Spanish"
                {"เลือกสี", "แสดงปี", "ัเริ่มวันจันทร์", "สั่น", "เลขสัปดาห์"},                                      //"Thai", by iamping
                {"Renk seç","Yılı göster","Pazartesi 1", "Vibrate", "Weeks No"},                   //"Turkish"
        };

        public static String[] getDays(int lang) {
            return translations.days[lang % translations.codes.length];
        }

        public static String[] getMonths(int lang) {
            return translations.months[lang % translations.codes.length];
        }
    }

    // Default language
    private int lang_number = 0;

    APtranslations () {

    }
    APtranslations (String code) {
        this.setLang(code);
    }

    public String getCode() {
        return translations.codes[this.lang_number];
    }

    public String getName() {
        return translations.languages[this.lang_number];
    }

    public String[] getOther() {return translations.other[this.lang_number];}

    public void nextLang() {
        this.lang_number = (this.lang_number + 1) % translations.codes.length;
    }
    public void setLang(String code) {
        // Find lang number by code
        int index = Arrays.asList(translations.codes).indexOf(code);
        if (index >= 0) {
            this.lang_number = index;
        }
    }

    public String[] getDays() {
        return translations.getDays(this.lang_number);
    }

    public String[] getMonths() {return translations.getMonths(this.lang_number);}

    public boolean isRtL() {
        return (translations.codes[this.lang_number]=="he");
    }
}
