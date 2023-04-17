package shpp.maslak.task3.data.model


import android.util.Log
import com.github.javafaker.Faker
import kotlinx.coroutines.flow.MutableStateFlow

class ContactGenerator {

    private val faker = Faker.instance()

    private fun generateContacts(): MutableStateFlow<List<Contact>> {
        Log.d("myLog", "generate contacts")
        return MutableStateFlow(
            List(5) { index -> randomContact(id = index + 1L) }
        )
    }

    fun getContacts(): MutableStateFlow<List<Contact>>{
        return  generateContacts()
    }

    private fun randomContact(id: Long): Contact {
        Log.i("myTag", "create contact")
        return Contact(
            id = id,
            avatar = IMAGES[id.rem(IMAGES.size).toInt()],
            userName = faker.name().fullName(),
            address = faker.address().fullAddress(),
            career = faker.artist().name()
        )
    }


    companion object {
        private val IMAGES = mutableListOf(
            "https://media.istockphoto.com/id/1450677469/photo/shop-glasses-and-eyes-of-black-child-with-vision-healthcare-frame-check-or-choice-in-retail.jpg?b=1&s=170667a&w=0&k=20&c=Qsu32r57KmaTRSKEOAfkcEZ6Za-rRVYxAOkIk-5d2SE=",
            "https://media.istockphoto.com/id/403040788/photo/portrait-of-asian-girl-looking-at-camera-outdoor-focus-on-face.jpg?b=1&s=170667a&w=0&k=20&c=LKgda_71os2Qvf_LztfmtL_iQbZw7p_whHJSjNk68w8=",
            "https://media.istockphoto.com/id/1384357158/photo/portrait-of-a-beautiful-mexican-woman.jpg?b=1&s=170667a&w=0&k=20&c=sNzHC0E61lT6LYJ9XPmnUTGhqLxDtusrxbm8YcP1Qic=",
            "https://media.istockphoto.com/id/1369508766/photo/beautiful-successful-latin-woman-smiling.jpg?b=1&s=170667a&w=0&k=20&c=owOOPDbI6VOp1xYA4smdTNSHxjcJGRtGfVXx24g6J4c=",
            "https://media.istockphoto.com/id/1335941248/photo/shot-of-a-handsome-young-man-standing-against-a-grey-background.jpg?b=1&s=170667a&w=0&k=20&c=Dl9uxPY_Xn159JiazEj0bknMkLxFdY7f4tK1GtOGmis=",
            "https://media.istockphoto.com/id/1341667252/photo/be-so-good-that-youll-be-recognized-internationally.jpg?b=1&s=170667a&w=0&k=20&c=HZKfxo9SydmE80Hi3F_YIpdK_oM5v4yTGXXy7NdGayM=",
            "https://media.istockphoto.com/id/1417635888/photo/cheerful-mid-adult-woman-face-mature-model-looking-at-camera-and-smiling.jpg?b=1&s=170667a&w=0&k=20&c=QNYoqHoInSF9q_1N8zJV_syM-KeglG8de06A1IFD6bw=",
            "https://media.istockphoto.com/id/1341667252/photo/be-so-good-that-youll-be-recognized-internationally.jpg?b=1&s=170667a&w=0&k=20&c=HZKfxo9SydmE80Hi3F_YIpdK_oM5v4yTGXXy7NdGayM=",
            "https://media.istockphoto.com/id/1364917563/photo/businessman-smiling-with-arms-crossed-on-white-background.jpg?b=1&s=170667a&w=0&k=20&c=KDO6yy-rASso-b0tI6Euv2um6GxXJ6QoQr0qioETwJE=",
            "https://media.istockphoto.com/id/1357723739/photo/studio-portrait-of-a-smiling-young-latin-woman.jpg?b=1&s=170667a&w=0&k=20&c=RIMvJI9S1mZytKJydukxUF4hRoyVbR1W3ix6gsdo72I=",
            "https://media.istockphoto.com/id/1327765575/photo/portrait-of-gorgeous-asian-woman-with-long-dark-hair-laughing-at-camera-with-beautiful-smile.jpg?b=1&s=170667a&w=0&k=20&c=U8JKroRKQ4_ev7-M_A_iCrRXq4IDAfSEGgru4WUV3hE=",
            "https://media.istockphoto.com/id/1412730914/photo/confident-young-entrepreneur.jpg?b=1&s=170667a&w=0&k=20&c=fB94dfCRcCIUBIjQEO9UJZCOxCkKFhCwwthXI-ZZc1c=",
            "https://media.istockphoto.com/id/1386479313/photo/happy-millennial-afro-american-business-woman-posing-isolated-on-white.jpg?b=1&s=170667a&w=0&k=20&c=ahypUC_KTc95VOsBkzLFZiCQ0VJwewfrSV43BOrLETM="
        )
    }
}