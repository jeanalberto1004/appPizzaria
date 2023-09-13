package br.com.inf3cm.priceresearch;

// 4

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.slider.LabelFormatter;
import com.google.android.material.slider.RangeSlider;
import com.google.android.material.slider.Slider;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ProductAddEditActivity extends AppCompatActivity {

    private static final String TAG = "ProductAddEditActivity";

    String mMode;

    TextView mTextViewCancel;

    Button mButtonSaveProduct;
    EditText mEditTextName, mEditTextPrice;

    RatingBar mRatingBarPricePerception;

    Slider mSliderConsumptionCycle;

    ChipGroup mChipGroupOption;
    Chip mChip0, mChip1, mChip2, mChip3, mChip4;
    int vSliderValue;
    int vChipValue;

    String mMessage;

    private void performCancel() {  //executeCancel
        Intent mIntentReply = new Intent();
        setResult(RESULT_CANCELED, mIntentReply);
        finish();
    }

    public class ClickMyCancel implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            performCancel();
        }
    }

    private boolean isInputValid() { //  isInputInvalid  isRequired
        if (TextUtils.isEmpty(mEditTextName.getText()) ||
                TextUtils.isEmpty(mEditTextPrice.getText()) ||
                vChipValue == 0) {
            return true;
        } else {
            return false;
        }

    }


    Intent mIntentResponse = new Intent();


    public class SliderMySlide implements Slider.OnChangeListener {  //SliderMyTouch

        @Override
        public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
            vSliderValue = (int) value;
            mChip0.setText(vSliderValue + "X");
        }

    }


    private void setChipSelected(int vChipValue) { //setChipNumber

        switch (vChipValue) {
            case 1:
                mChip1.setChecked(true);
                break;
            case 2:
                mChip2.setChecked(true);
                break;
            case 3:
                mChip3.setChecked(true);
                break;
            case 4:
                mChip4.setChecked(true);
                break;
        }

    }

};







/**
 Este código está relacionado com a adição e edição de produtos em uma aplicação Android. Vamos comentar cada parte do código.

A classe ProductAddEditActivity estende AppCompatActivity, o que significa que esta é uma Activity que suporta funcionalidades específicas da ActionBar da Biblioteca de Suporte do Android.

O código define várias variáveis de instância (objetos) para armazenar referências para vários elementos de UI, como TextViews, Buttons, EditTexts, RatingBar, Slider e Chips.

O método performCancel() é usado para terminar a atividade atual e voltar para a atividade anterior.

A classe interna ClickMyCancel implementa um OnClickListener, que é acionado quando o usuário clica no botão cancelar.

O método isInputValid() [isRequired] verifica se todos os campos necessários foram preenchidos antes de salvar o produto.

O método saveProduct() é usado para salvar o produto. Se os campos necessários não forem preenchidos, ele exibe um Toast informando ao usuário que as informações obrigatórias não foram fornecidas. Se os campos necessários forem preenchidos, ele cria um novo Intent, coloca os detalhes do produto no Intent como extras e define o resultado da atividade como RESULT_OK.

A classe interna ClickMyButtonSave implementa um OnClickListener, que é acionado quando o usuário clica no botão para salvar o produto.

A classe interna SliderMyTouch implementa um OnChangeListener, que é acionado quando o usuário move o slider.

A classe interna ChipGroupMySelectionChip implementa um OnCheckedStateChangeListener, que é acionado quando o estado de seleção de um chip muda.

O método setChipNumber() é usado para selecionar um chip específico no grupo de chips.

A classe interna FormatInputNumber implementa um InputFilter, que é usado para formatar o input de um EditText.

O método onCreate() é chamado quando a atividade é criada. Ele configura a interface do usuário e define os listeners para vários elementos de UI.

Os métodos onCreateOptionsMenu() e onOptionsItemSelected() são usados para criar e lidar com ações no menu de opções da atividade.

A classe representa uma atividade que permite ao usuário adicionar ou editar um produto. A atividade inclui campos para o nome e o preço do produto, uma barra de classificação para a percepção do preço, um slider para o ciclo de consumo, e um grupo de chips para o ciclo de consumo.




 Em geral, o código é bem estruturado, com boa organização e uso apropriado dos recursos da linguagem Java e da plataforma Android. Aqui estão algumas observações detalhadas:

 Organização do código: A estrutura da classe ProductAddEditActivity é organizada de maneira clara. As variáveis são bem nomeadas, o que torna o código mais legível. Além disso, o uso de classes internas para lidar com eventos de clique e mudanças de valor é uma boa prática, pois permite agrupar o código relacionado de forma clara.

 Tratamento de exceções: No código apresentado, não há evidência de um tratamento robusto de exceções. Isso pode levar a erros inesperados em tempo de execução. Por exemplo, no método saveProduct(), Float.valueOf(mEditTextPrice.getText().toString()) pode lançar uma NumberFormatException se o texto não puder ser parseado para um float. Para melhorar o código, pode-se incluir o bloco try-catch para lidar com essas exceções.

 Documentação do código: Embora o código seja bastante legível, ele carece de comentários em muitas partes. Adicionar comentários em pontos-chave do código pode melhorar significativamente a manutenibilidade e legibilidade.

 Uso de recursos do Android: O código faz bom uso de vários recursos do Android, como Intents, Toasts, Activities, Buttons, EditTexts, RatingBars, Sliders e Chips. Além disso, os recursos de Material Design são bem utilizados para melhorar a aparência e a experiência do usuário.

 Método isRequired(): O nome do método isRequired() pode ser um pouco confuso. Com base no nome, pode parecer que o método verifica se algo é necessário, mas na realidade ele verifica se algum dos campos necessários está vazio. Talvez um nome como areRequiredFieldsEmpty() ou isInputValid() seria mais apropriado.

 Internacionalização: A mensagem de erro "Mandatory information" está hardcoded em inglês. Isso pode ser um problema para usuários que não falam inglês. Uma boa prática é usar arquivos de recursos de strings para permitir a internacionalização do aplicativo.

 Em resumo, este é um bom exemplo de código para um aplicativo Android. No entanto, sempre há espaço para melhorias, como o tratamento de exceções, melhor documentação do código, nomes de métodos mais claros e internacionalização.




* */