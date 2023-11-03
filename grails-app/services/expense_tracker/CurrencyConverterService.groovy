package expense_tracker

import grails.plugins.rest.client.RestBuilder
import grails.plugins.rest.client.RestResponse
import grails.gorm.transactions.Transactional

@Transactional
class CurrencyConverterService {

    def convertCurrencyZARtoUSD(double value) {
        // dummy value used since the rest call isn't working
        def rate = 0.0542898681
        return Math.round(value * rate * 100) / 100
    }

    double getExchangeRate() {
        RestBuilder rest = new RestBuilder()

        // can't get this to work, just always returns empty json, all docs are very inconsistent or doesn't exist, tried many options, nothing works
        // fixer.io's free version doesn't allow you to use ZAR at all, only a few currencies are available, freecurrencyapi.com is completely free
        RestResponse resp = rest.get("https://api.freecurrencyapi.com/v1/latest?apikey=fca_live_vZ9PPBQ3ebn4EnCW34JvJm2a59AUn5jhXy6C8j3a&currencies=USD&base_currency=ZAR")
        def json = resp.json
        def data = json.data

        // dummy value used since the rest call isn't working
        return 0.0542898681
    }
}
