package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText edtHoTen, edtCMND, edtBoSung;
    RadioButton radTrungCap, radCaoDang, radDaiHoc;
    CheckBox cbxDocBao, cbxDocSach, cbxDocCoding;
    Button btnGuiThongTin;
    RadioGroup group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtHoTen = findViewById(R.id.edtHoTen);
        edtCMND = findViewById(R.id.edtCMND);
        edtBoSung = findViewById(R.id.edtBoSung);

        radTrungCap = findViewById(R.id.radTrungCap);
        radCaoDang = findViewById(R.id.radCaoDang);
        radDaiHoc = findViewById(R.id.raDaiHoc);

        cbxDocBao = findViewById(R.id.cbxDocBao);
        cbxDocSach = findViewById(R.id.cbxDocSach);
        cbxDocCoding = findViewById(R.id.cbxCoding);

        btnGuiThongTin = findViewById(R.id.btnGuiThongTin);

        btnGuiThongTin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //kiem tra ten
                String ten = edtHoTen.getText().toString();
                ten = ten.trim();
                if (ten.length() < 3) {
                    edtHoTen.requestFocus();
                    edtHoTen.selectAll();
                    Toast.makeText(MainActivity.this, "Tên phải >= 3 ký tự", Toast.LENGTH_LONG).show();
                    return;
                }
                //kiem tra cmnd
                String cmnd = edtCMND.getText().toString();
                cmnd = cmnd.trim();
                if (cmnd.length() != 9) {
                    edtCMND.requestFocus();
                    edtCMND.selectAll();
                    Toast.makeText(MainActivity.this, "CMND phải đúng 9 ký tự", Toast.LENGTH_LONG).show();
                }
                //kiem tra bang
                String bang = "";
                group = findViewById(R.id.group);
                int id = group.getCheckedRadioButtonId();
                if (id == -1) {
                    Toast.makeText(MainActivity.this, "Phải chọn bằng cấp", Toast.LENGTH_LONG).show();
                    return;
                }
                RadioButton rad = findViewById(id);
                bang = rad.getText() + "";
                //Kiểm tra sở thích
                String sothich = "";
                if (cbxDocBao.isChecked())
                    sothich += cbxDocBao.getText() + "\n";
                if (cbxDocSach.isChecked())
                    sothich += cbxDocSach.getText() + "\n";
                if (cbxDocCoding.isChecked())
                    sothich += cbxDocCoding.getText() + "\n";
                String bosung = edtBoSung.getText() + "";
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Thông tin cá nhân");
                builder.setPositiveButton("Đóng", new
                        DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                                dialog.cancel();
                            }
                        });
                //tạo nội dung
                String msg = ten + "\n";
                msg += cmnd + "\n";
                msg += bang + "\n";
                msg += sothich;
                msg += "—————————–\n";
                msg += "Thông tin bổ sung:\n";
                msg += bosung + "\n";
                msg += "—————————–";
                builder.setMessage(msg);//thiết lập nội dung
                builder.create().show();//hiển thị Dialog
            }

            public void onBackPressed() {
                AlertDialog.Builder b = new
                        AlertDialog.Builder(MainActivity.this);
                b.setTitle("Question");
                b.setMessage("Are you sure you want to exit?");

                b.setPositiveButton("Yes", new DialogInterface.
                        OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                b.setNegativeButton("No", new
                        DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                b.create().show();
            }
        });
    }
}