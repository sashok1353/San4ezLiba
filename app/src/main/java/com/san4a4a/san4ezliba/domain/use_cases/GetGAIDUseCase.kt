package com.san4a4a.san4ezliba.domain.use_cases

import android.content.Context
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.common.GooglePlayServicesRepairableException
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

class GetGAIDUseCase @Inject constructor(@ApplicationContext private val context: Context) {
    suspend operator fun invoke() : String? {
        return withContext(Dispatchers.IO) {
            try {
                AdvertisingIdClient.getAdvertisingIdInfo(context).id
            } catch (e: GooglePlayServicesNotAvailableException) {
                e.printStackTrace()
                null
            } catch (e: GooglePlayServicesRepairableException) {
                e.printStackTrace()
                null
            } catch (e: IOException) {
                e.printStackTrace()
                null
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }
}