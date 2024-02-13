module core {
    requires com.fasterxml.jackson.databind;

    exports core;
    exports json;

    opens json;
    opens core;
}
