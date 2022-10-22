package br.ulbra.appfolhapgto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText edtNomeFuncionario, edtSalarioBruto, edtNumeroFilhos;
    RadioGroup rgSexo;
    RadioButton rbFeminino, rbMasculino;
    TextView txtNome, txtINSS, txtIR, txtSalarioLiquido;
    Button btCalcular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtNomeFuncionario = findViewById(R.id.edtNomeFuncionario);
        edtSalarioBruto = findViewById(R.id.edtSalarioBruto);
        edtNumeroFilhos = findViewById(R.id.edtNumeroFilhos);
        rgSexo = findViewById(R.id.rgSexo);
        rbFeminino = findViewById(R.id.rbFeminino);
        rbMasculino = findViewById(R.id.rbMasculino);
        txtNome = findViewById(R.id.txtNome);
        txtINSS = findViewById(R.id.txtINSS);
        txtIR = findViewById(R.id.txtIR);
        txtSalarioLiquido = findViewById(R.id.txtSalarioLiquido);
        btCalcular = findViewById(R.id.btCalcular);

        btCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double salarioB;
                int nFihos;
                String nome;
                String sexo;

                nome = edtNomeFuncionario.getText().toString();
                salarioB = Double.parseDouble(edtSalarioBruto.getText().toString());
                nFihos = Integer.parseInt(edtNumeroFilhos.getText().toString());
                txtNome.setText(selectSexo());
                txtINSS.setText(String.valueOf(inss(salarioB)));
                txtIR.setText(String.valueOf(ir(salarioB)));
                txtSalarioLiquido.setText(String.valueOf(salFamilia(salarioB, nFihos)));
            }
        });

    }
        public double inss(double salario) {
            double novoSalario = 0;
            if (salario <= 1212.00) {
                novoSalario = salario * 0.075;
            } else if (salario >= 1212.01 && salario <= 2427.35) {
                novoSalario = salario * 0.09;
            } else if (salario >= 2427.36 && salario <= 3641.03) {
                novoSalario = salario * 0.12;
            } else {
                novoSalario = salario * 0.14;
            }
            return novoSalario;
        }

        public double ir(double salario) {
            double ss = 0;
            if (salario <= 1903.98) {
                ss = salario * 0;
            } else if (salario >= 1903.99 && salario <= 2826.65) {
                ss = salario * 0.075;
            } else if (salario >= 2826.66 && salario <= 3751.05) {
                ss = salario * 0.15;
            } else {
                ss = salario * 0.225;
            }
            return ss;
        }

        public String selectSexo() {
            String sexo = null;
            if (rbMasculino.isChecked()) {
                sexo = "Sr. " + edtNomeFuncionario.getText().toString();
            } else {
                sexo = "Sra. " + edtNomeFuncionario.getText().toString();
            }
            return sexo;
        }

        public double salFamilia(double salario, int filho) {
            double sf = 0;

            if (salario <= 1212.00) {
                sf = filho * 56.47;
            }
            return sf;
        }

        public double calcSalarioLiq(double salarioBruto, double INSS, double IR, double salarioFamilia) {
            double salarioLiquido = salarioBruto - INSS - IR + salarioFamilia;
            return salarioLiquido;
    }
}