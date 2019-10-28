package com.dinodevs.pacecalendarwidget;
import java.util.Arrays;

/**
 * Created by GreatApo on 08/04/2018.
 */

public class APtranslations {
    public static class translations {

        public static String[] codes = {
                "en", "bg", "zh", "hr", "cz", "dk", "nl", "fr", "de", "gr", "he", "hu", "it", "ja", "kr", "pl", "pt", "ro", "ru", "sk", "es", "th", "tr", "vn"
        };

        public static String[] languages = {
                "English", "Български", "中文", "Hrvatski", "Czech", "Dansk", "Nederlands", "Français", "Deutsch", "Ελληνικά", "עברית", "Magyar", "Italiano", "日本語", "한국어", "Polski", "Português", "Română", "Русский", "Slovenčina", "Español", "ไทย", "Türkçe", "Tiếng Việt",
        };

        public static String[] languages_en = {
                "English", "Bulgarian", "Chinese", "Croatian", "Czech", "Danish", "Dutch", "French", "German", "Greek", "Hebrew", "Hungarian", "Italian", "Japanese", "Korean", "Polish", "Portuguese", "Romanian", "Russian", "Slovak", "Spanish", "Thai", "Turkish", "Vietnamese",
        };

        public static String[][] days = {
                {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"},
                {"Понеделник", "Вторник", "Сряда", "Четвъртък", "Петък", "Събота", "Неделя"},
                {"星期天", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"},
                {"Nedjelja", "Ponedjeljak", "Utorak", "Srijeda", "Četvrtak", "Petak", "Subota"},
                {"Neděle","Pondělí", "Úterý", "Středa", "Čtvrtek", "Pátek", "Sobota"},
                {"Søndag","Mandag", "Tirsdag", "Onsdag", "Torsdag", "Fredag", "Lørdag"},
                {"Zondag", "Maandag", "Dinsdag", "Woensdag", "Donderdag", "Vrijdag", "Zaterdag"},
                {"Dimanche", "Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi"},
                {"Sonntag", "Montag", "Dienstag", "Mittwoch", "Donnerstag", "Freitag", "Samstag"},
                {"Κυριακή", "Δευτέρα", "Τρίτη", "Τετάρτη", "Πέμπτη", "Παρασκευή", "Σάββατο"},
                {"ש'","ו'","ה'","ד'","ג'","ב'","א'"},
                {"Vasárnap", "Hétfő", "Kedd", "Szerda", "Csütörtök", "Péntek", "Szombat"},
                {"Domenica", "Lunedì", "Martedì", "Mercoledì", "Giovedì", "Venerdì", "Sabato"},
                {"日曜日", "月曜日", "火曜日", "水曜日", "木曜日", "金曜日", "土曜日"},
                {"일", "월", "화", "수", "목", "금", "토"},
                {"Niedziela", "Poniedziałek", "Wtorek", "Środa", "Czwartek", "Piątek", "Sobota"},
                {"Domingo", "Segunda", "Terça", "Quarta", "Quinta", "Sexta", "Sábado"},
                {"Duminică", "Luni", "Marți", "Miercuri", "Joi", "Vineri", "Sâmbătă"},
                {"воскресенье", "понедельник", "вторник", "среда", "четверг", "пятница", "суббота"},
                {"Nedeľa", "Pondelok", "Utorok", "Streda", "Štvrtok", "Piatok", "Sobota"},
                {"Domingo", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado"},
                {"อา", "จ", "อ", "พ", "พฤ", "ศ", "ส"},
                {"Pazar", "Pazartesi", "Salı", "Çarşamba", "Perşembe", "Cuma", "Cumartesi"},
                {"CN","T2", "T3", "T4", "T5", "T6", "T7"}
        };

        public static String[][] months = {
                {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"},
                {"Януари", "Февруари", "Март", "Април", "май", "Юни", "Юли", "Август", "Септември", "Октомври", "Ноември" , "Декември"},
                {"一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"},
                {"Siječanj", "Veljača", "Ožujak", "Travanj", "Svibanj", "Lipanj", "Srpanj", "Kolovoz", "Rujan", "Listopad", "Studeni", "Prosinac"},
                {"Leden", "Únor", "Březen", "Duben", "Květen", "Červen", "Červenec", "Srpen", "Září", "Říjen", "Listopad", "Prosinec"},
                {"Januar", "Februar", "Marts", "April", "Maj", "Juni", "Juli", "August", "September", "Oktober", "November", "December"},
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
                {"Ianuarie", "Februarie", "Martie", "Aprilie", "Mai", "Iunie", "Iulie", "August", "Septembrie", "Octombrie", "Noiembrie", "Decembrie"},
                {"январь", "февраль", "март", "апрель", "май", "июнь", "июль", "август", "сентябрь", "октябрь", "ноябрь", "декабрь"},
                {"Január", "Február", "Marec", "Apríl", "Máj", "Jún", "Júl", "August", "September", "Október", "November", "December"},
                {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"},
                {"มกราคม", "กุมภาพันธ์", "มีนาคม", "เมษายน", "พฤษภาคม", "มิถุนายน", "กรกฎาคม", "สิงหาคม", "กันยายน", "ตุลาคม", "พฤศจิกายน", "ธันวาคม"},
                {"Ocak", "Şubat", "Mart", "Nisan", "Mayıs", "Haziran", "Temmuz", "Ağustos", "Eylül", "Ekim", "Kasım", "Aralık"},
                {"Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6", "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"}
        };

        public static String[][] other = {
                {"Select color","Show year","Monday 1st", "Vibrate", "Weeks No"},                  //"English",
                {"Изберете цвят", "Показване на година", "Първо понеделник", "Вибриране", "Седмица"},// Bulgarian
                {"选择颜色","显示年份","星期一放1", "颤动", "周数"},                                    //"Chinese",
                {"Odaberite boju","Prikaži godinu","Od ponedjeljka", "Vibriraj", "Br tjedna"},     //Croatian,
                {"Vyberte barvu", "Zobrazit rok", "Od pondělka", "Vibrovat", "Týdny č"},           //"Czech",
                {"Vælg farve","Vis år","Mandag først","Vibrer","Uge nr"},                          //"Danish", by thra1982
                {"Selecteer kleur","Toon jaar","Maandag 1e", "Trillen", "Weken Nee"},              //"Dutch"
                {"Choisissez la couleur","Afficher l'année","Mettez 1er", "Vibrer", "Semaines Non"},  //"French",
                {"Farbe auswählen","Jahr anzeigen","Montag erste", "Vibrieren", "Wochen Nr"},         //"German",
                {"Επιλογή χρώματος","Εμφάνιση χρονιάς","Δευτέρα 1η", "Δόνηση", "Αρ εβδομάδας"},    //"Greek",
                {"בחירת צבע","הצג שנה","החל מיום שני", "רטט", "שבועות לא"},                         //"Hebrew"
                {"Válasszon színt","Az év megjelenítése","Hétfő 1", "Rezeg", "Hetek Nem"},        //"Hungarian",
                {"Seleziona colore","Mostra l'anno","Lunedì 1°", "Vibrare", "Settimane n"},        //"Italian",
                {"色を選択","年を表示","月曜日に入れて", "バイブレーション", "週なし"},                   //"Japanese",
                {"색상 선택","연도 표시","색상 선택", "진동", "주차 표시"},                             //"Korean", by eastway11
                {"Wybierz kolor","Pokaż rok","Poniedziałek 1.", "Wibrować", "Tygodnie nr"},        //"Polish",
                {"Selecione a cor","Mostrar ano","Segunda-feira", "Vibrar", "Semanas Não"},        //"Portuguese",
                {"Alege Culoarea","Arată Anul","Luni Prima", "Vibrație", "Nr. Săptămână"},         //"Romanian",
                {"Выбор цвета","Показ года","Пон. 1-й", "вибрировать", "Недели нет"},              //"Russian",
                {"Vyberte farbu","Zobraziť rok","Pondelok 1.", "Vibrovať", "Týždne č"},            //"Slovak",
                {"Seleccionar el color","Mostrar año","Lunes 1°", "Vibrar", "Semanas No"},         //"Spanish"
                {"เลือกสี", "แสดงปี", "ัเริ่มวันจันทร์", "สั่น", "เลขสัปดาห์"},                                      //"Thai", by iamping
                {"Renk seç","Yılı göster","Pazartesi 1", "Titremek", "Hafta Yok"},                 //"Turkish"
                {"Chọn màu","Hiện năm","Thứ 2 1st","Báo rung","Tuần thứ"}                          //"Vietnamese", by chienkd
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
