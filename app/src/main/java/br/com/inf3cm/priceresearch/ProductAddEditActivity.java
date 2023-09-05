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

    private void performCancel(){  //executeCancel
        Intent mIntentReply = new Intent();
        setResult(RESULT_CANCELED, mIntentReply);
        finish();
    }

    public class ClickMyCancel implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            performCancel();
        }
    }

    private boolean isInputValid() { //  isInputInvalid  isRequired
        if( TextUtils.isEmpty(mEditTextName.getText()) ||
            TextUtils.isEmpty(mEditTextPrice.getText()) ||
                vChipValue == 0 )
        {
            return true;
        } else {
            return false;
        }

    }

    private void saveProduct(){ //setSaveButtonProduct
        if(isInputValid()){
           // mMessage = "Mandatory information"; //hardcoded - internacionalizacao
            mMessage = getString(R.string.text_mandatory_information);
         //   Toast.makeText(getApplicationContext(), mMessage, Toast.LENGTH_SHORT).show();

            Snackbar.make(findViewById(R.id.layout_add_edit_product), mMessage, Snackbar.LENGTH_SHORT).show();

            return;
        }

        Intent mIntentResponse = new Intent();

        mMode = "ADD";
        int vId = getIntent().getIntExtra("EXTRA_ID", -1);
        if( vId != -1 ) {
            mMode = "EDIT";
            mIntentResponse.putExtra("EXTRA_ID" , vId);
        }

        String mStringName = mEditTextName.getText().toString().trim();
        mIntentResponse.putExtra("EXTRA_NAME", mStringName);

        float vPrice = Float.valueOf(mEditTextPrice.getText().toString());  //erro de conversao 0,00   o separador decimal
        mIntentResponse.putExtra("EXTRA_PRICE", vPrice);

        float vPerception = mRatingBarPricePerception.getRating();
        mIntentResponse.putExtra("EXTRA_PERCEPTION_PRICE", vPerception);

        mIntentResponse.putExtra( "EXTRA_AMOUNT_CONSUMPTION", vSliderValue);

        mIntentResponse.putExtra("EXTRA_CONSUMPTION_CYCLE", vChipValue);

        mIntentResponse.putExtra("EXTRA_MODE", mMode);

        setResult(RESULT_OK, mIntentResponse);
        finish();
    }


    public class ClickMyButtonSave implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            saveProduct();
        }
    }

    public class SliderMySlide implements Slider.OnChangeListener{  //SliderMyTouch

        @Override
        public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
            vSliderValue = (int)value;
            mChip0.setText( vSliderValue + "X");
        }

    }

    public class ChipGroupMySelectionChip implements ChipGroup.OnCheckedStateChangeListener{
        @Override
        public void onCheckedChanged(@NonNull ChipGroup group, @NonNull List<Integer> checkedIds) {
            switch (group.getCheckedChipId()){
                case R.id.chip_consumption_cycle_1: vChipValue=1; break;
                case R.id.chip_consumption_cycle_2: vChipValue=2; break;
                case R.id.chip_consumption_cycle_3: vChipValue=3; break;
                case R.id.chip_consumption_cycle_4: vChipValue=4; break;
                default: vChipValue = 0;
            }
            //Toast.makeText(getApplicationContext(), "Chip " + vChipValueConsumptionCycle, Toast.LENGTH_SHORT).show();
        }
    }

    private void setChipSelected (int vChipValue){ //setChipNumber

        switch (vChipValue){
            case 1: mChip1.setChecked(true); break;
            case 2: mChip2.setChecked(true); break;
            case 3: mChip3.setChecked(true); break;
            case 4: mChip4.setChecked(true); break;
        }

    }


    // https://pt.stackoverflow.com/questions/279149/tratamento-casas-decimais-edittext
    public class FormatInputNumber implements InputFilter{
        int vDigitsBeforeZero;
        int vDigitsAfterZero;
        Pattern mPattern;
        private static final int DIGITS_BEFORE_ZERO_DEFAULT = 100; //Antes
        private static final int DIGITS_AFTER_ZERO_DEFAULT = 100; //Depois

        public FormatInputNumber(Integer digitsBeforeZero, Integer digitsAfterZero) {
            // É uma expressão condicional ternária
            // (também conhecida como operador ternário)
            vDigitsBeforeZero = (digitsBeforeZero != null ? digitsBeforeZero : DIGITS_BEFORE_ZERO_DEFAULT);
            vDigitsAfterZero = (digitsAfterZero != null ? digitsAfterZero : DIGITS_AFTER_ZERO_DEFAULT);
            mPattern = Pattern.compile("-?[0-9]{0," + (vDigitsBeforeZero) + "}+((\\.[0-9]{0," + (vDigitsAfterZero) + "})?)||(\\.)?");
        }

        // Spanned representar um texto que contém estilos (spans)
        // aplicados a diferentes partes do texto.
        @Override
        public CharSequence filter(CharSequence charSequenceSource, int vStart, int vEnd, Spanned spannedDest, int vDestStart, int vDestEnd) {
            String replacement = charSequenceSource.subSequence(vStart, vEnd).toString();
            String newVal = spannedDest.subSequence(0, vDestStart).toString() + replacement + spannedDest.subSequence(vDestEnd, spannedDest.length()).toString();
            Matcher matcher = mPattern.matcher(newVal);
            if (matcher.matches()){
                return  null;
            }
            if(TextUtils.isEmpty(charSequenceSource)){
                return spannedDest.subSequence(vDestStart, vDestEnd);
            } else {
                return "";
            }
        }
    }



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_product);

//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
// no layout
        //    android:focusable="true"
        //    android:focusableInTouchMode="true"

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        mTextViewCancel = findViewById(R.id.textView_cancel_product_add_edit);
        mTextViewCancel.setOnClickListener(new ClickMyCancel());

        mButtonSaveProduct = findViewById(R.id.button_save_product);
        mButtonSaveProduct.setOnClickListener(new ClickMyButtonSave());

        mEditTextName = findViewById(R.id.editText_product_name);
        mEditTextPrice = findViewById(R.id.editText_product_price);
        mRatingBarPricePerception = findViewById(R.id.rating_product_price_perception);

// https://www.youtube.com/watch?v=IibybM4oM1w
        //https://www.geeksforgeeks.org/how-to-customise-mdc-sliders-in-android/
       mSliderConsumptionCycle = findViewById(R.id.slider_consumption_cycle);
       mSliderConsumptionCycle.addOnChangeListener(new SliderMySlide());

       mChipGroupOption = findViewById(R.id.chip_group_option);
       mChipGroupOption.setOnCheckedStateChangeListener(new ChipGroupMySelectionChip());

       mChip0 = findViewById(R.id.chip_consumption_cycle_0);
       mChip1 = findViewById(R.id.chip_consumption_cycle_1);
       mChip2 = findViewById(R.id.chip_consumption_cycle_2);
       mChip3 = findViewById(R.id.chip_consumption_cycle_3);
       mChip4 = findViewById(R.id.chip_consumption_cycle_4);

        Intent mIntentShow = getIntent(); //mIntentShowMode

        if(mIntentShow.hasExtra("EXTRA_ID")){
            setTitle("Product Edit");

            mEditTextName.setText(mIntentShow.getStringExtra("EXTRA_NAME"));

            double value = mIntentShow.getDoubleExtra("EXTRA_PRICE", 0); //hack - not running with float
            //String mStringPrice = String.format("%.2f", value);
            String mStringPrice = String.format(java.util.Locale.ROOT,"%.2f", value); //hack - show point separate
            mEditTextPrice.setText(mStringPrice);

            mEditTextPrice.setFilters(new InputFilter[]{new FormatInputNumber(10,2)});
            //https://pt.stackoverflow.com/questions/279149/tratamento-casas-decimais-edittext

            mRatingBarPricePerception.setRating(mIntentShow.getFloatExtra("EXTRA_PERCEPTION_PRICE", 0.0f));

            vSliderValue = mIntentShow.getIntExtra("EXTRA_AMOUNT_CONSUMPTION",1); //cycle
            mSliderConsumptionCycle.setValue(vSliderValue);

            vChipValue = mIntentShow.getIntExtra("EXTRA_CONSUMPTION_CYCLE",0); //freq
            setChipSelected(vChipValue);

        } else {
            setTitle("New Product");
            vSliderValue = 1;
            vChipValue = 0;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater mMenuInflater = getMenuInflater();
        mMenuInflater.inflate(R.menu.save_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch ( item.getItemId() ) {
            case R.id.action_menu_save:
                saveProduct();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}


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