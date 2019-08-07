/**
 *                           MIT License
 *
 *                 Copyright (c) 2019 Amr Elghobary
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package dev.amr.travelmantics.ui.adddeal

import android.net.Uri
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import dev.amr.travelmantics.util.ObservableViewModel

class DealUIModel : ObservableViewModel() {

    val areInputsReady = MediatorLiveData<Boolean>()

    val title = MutableLiveData("")
    val price = MutableLiveData("")
    val description = MutableLiveData("")
    val fileUri = MutableLiveData<Uri>(null)
    val imageReady = Transformations.map(fileUri) {
          it != null
    }

    init {
        areInputsReady.addSource(title) { areInputsReady.value = checkInputs() }
        areInputsReady.addSource(price) { areInputsReady.value = checkInputs() }
        areInputsReady.addSource(description) { areInputsReady.value = checkInputs() }
        areInputsReady.addSource(imageReady) { areInputsReady.value = checkInputs() }
    }

    private fun checkInputs(): Boolean {
        return !(
                title.value.isNullOrEmpty() ||
                        price.value.isNullOrEmpty() ||
                        description.value.isNullOrEmpty() ||
                        imageReady.value == false)
    }
}