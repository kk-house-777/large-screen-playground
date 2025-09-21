package example.large.screen.playground.core.config

object Config {

    var useAdaptiveLayouts: Boolean = false
        private set

    var enableListDetailLayout: Boolean = false
        private set

    var enableSupportingPaneLayout: Boolean = false
        private set

    fun setAdaptiveLayouts(enabled: Boolean) {
        useAdaptiveLayouts = enabled
    }

    fun setListDetailLayout(enabled: Boolean) {
        enableListDetailLayout = enabled
    }

    fun setSupportingPaneLayout(enabled: Boolean) {
        enableSupportingPaneLayout = enabled
    }

    fun resetToDefaults() {
        useAdaptiveLayouts = false
        enableListDetailLayout = false
        enableSupportingPaneLayout = false
    }
}