import java.text.ParseException;					//Timer�N���X�̌Ăяo��
import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;

import java.awt.event.MouseListener;				//�C�x���g���������݂��c�[
import java.awt.event.MouseEvent;

import java.io.*;									//Scanner�N���X�̌Ăяo��

import java.util.Random;							//Random�N���X�̌Ăяo��



public class DefuseBomb{

	public static void main(String[] args) {
			println("\n�u3����ɒn�����������܂��B�n�����~���܂��傤�v");							//�I�[�v�j���O
			timer();																				//�^�C�}�[�n����Arute���\�b�h���Ăяo��
			rute();
	}


	public static void rute() {																		//�v���C�I��
		enter();	
		while (true) {
			String[] title = {"\n�����R�[�h���Q�b�g�o����Ώ�����炵��","1:�����R�[�h����ɓ���� 2:�����Ɗy�ȕ��@��T�� 3:��������܂ŐQ��"};
			println(title);

			int answer = input();
					
			switch (answer) {																	//���͂ɂ���ď�������
				case 1:
					println("\n�R�[�h���͂͑S����3��");
					game1();
					break;
				case 2:
					println("\n�u�������悤�Ƃ���z�͔������܂��v");
					enter();
					exit();
				case 3:
					println("\n���₷�ݐ��E");
					rute3();
				case -1:
					println("\n�I��������͂��Ă�������");
					break;
				default:
					println("\n�I�����ʂ�ɓ����Ȃ������̂Ŕ������܂����B");
					exit();
			}
		}
	}



	public static void game1() {																//�����ăQ�[��
		while(true)	{
			enter();
			String[] title = {line(),"�͂��߂�0�`255�̂����̃����_���Ȑ����B�����J�Ƀq���g�������炵��",
							"���͂����������������傫�������������������Ă����B",line()};
			println(title);
			enter();

			println(line() + "\n�wGUESS THE NUMBER FOR 0�`255�x");
			Random rand = new Random();															//Random�N���X���g����0�`255�̐������쐬
			int i = rand.nextInt(256);

			for (int x = 0; x == x; x++) {														// ��������܂ł��肩����
				println(line());
				int number = input();

				if (i == number) {																	// �����̃`�F�b�N
					println("\n\n\n�w SUCCESS �x\n\n\n");
					game2();																			// �������Ă�����J��Ԃ��𔲂��Ď��̃Q�[����
				} else {
					println("ERROR");
					if (number == -1) {
						println("0�`255�̐�������͂��Ă�������");
					} else if (number > i) {																	// �傫���������̃`�F�b�N
						println("THE NUMBER IS MORE SMALLER");
					} else {
						println("THE NUMBER IS MORE BIGGER");
					}
				}
			}
		}
	}



	public static void game2() {																//�f�����ăQ�[��
		enter();
		String[] title = {line(),"���͑f��2,3,5,7,11,13�̂����̂ǂꂩ3�B",
							"\n���̃Q�[���ł́A�����p�[�g�Ɖ񓚃p�[�g������B\n�����p�[�g�œ����q���g�����ƂɁA�񓚃p�[�g��3�Ƃ������𓖂Ă���΃N���A�B",
							"\n�����p�[�g�F�uINPUT NUMBER?�v�̌�ɐ�������͂���B���������͂ł���̂�15�ȏ�̐����B\n���͂��������������̐����Ŋ���؂�邩�ǂ����ŁA3���ꂼ�ꂪ�uTRUE�v���uFALSE�v��Ԃ��B",
							"\n�񓚃p�[�g�F�u0�v����͂���Ɖ񓚉�ʂɈڂ�̂ŁA�������Ǝv���f�������Ԃɓ�����B3�Ƃ���������΃N���A�B",line()};
		println(title);
		enter();

		int[] answer = new int[3];
		int[] input = new int[3];

		for (int i = 0; i < answer.length; i++) {													//�����_����3�̑f����ݒ�
		
			Random rand = new Random();
			answer[i] = rand.nextInt(14);

			while (isPrime(answer[i])) {
				answer[i] = rand.nextInt(14);
			}

			for (int j = i - 1; j >= 0; j--) {															//�d�����Ȃ��f�����o��܂ŌJ��Ԃ�
				while (answer[i] == answer[j] || isPrime(answer[i])) {
					answer[i] = rand.nextInt(14);
				}
			}
		}

		while(true) {																				//�Q�[������ �N���A����܂ŌJ��Ԃ�
			println(line() + "\nINPUT NUMBER?\n");
			int Q = input();
			if (Q == 0) {																	//0�Ɠ��͂ŉ񓚉�ʂֈړ�
				println(line());
				println("\nANSWER?\n");
				int count = 0;
				for (int i = 0; i < answer.length; i++) {											//�񓚕���
					System.out.print("\n" + (i+1) + "�ځF");
					input[i] = input();
					if (input[i] == -1) {
						println("�w2�`13�̐�������͂��Ă��������x");
					} else if (input[i] == answer[i]) {													//�����Ȃ�΃J�E���g�𑝂₵�R�J�E���g�Ŏ��̃Q�[����
						println("TRUE");
						count++;
						if (count == 3) {
							println("\n\n\n�w SUCCESS �x\n\n\n");
							game3();
						}
					} else {
						println("FALSE");
					}
				}
				println(line());

			} else if (Q >= 15){																	//������������ 15�ȏ�̐������󂯕t����
				for (int i = 0; i < answer.length; i++) {
					System.out.print((i+1) + "�ځF");													//���͂��������������̐����Ŋ���؂�邩�ǂ�����true��false��\������
					if (Q % answer[i] == 0) {
						System.out.print("TRUE  ");
					} else {
						System.out.print("FALSE  ");
					}
				}
			} else {																				//14�ȉ��̐����ł̓��b�Z�[�W�����\�������[�v
				println("�w15�ȏ�̐�������͂��Ă��������x");
			}
		}//while
	}//method



	public static void game3() {																	//4���̐�������
		enter();
		String title[] = {line(),"�Ō�̓����_����4���̐����B�Ȃ�����������2�x�g���Ȃ�",
							"�܂��e����0�`9�̐�������͂���ƃq���g���o��B\n���������������Ă���΁uHIT�v�A�����͍����Ă��邪�����Ⴄ�ꍇ�́uBLOW�v���J�E���g�����B\n�uHIT:4�v�A�܂萳���̐���4�����Ԓʂ�ɓ�������΃N���A�B",line()};
		println(title);
		enter();
		println(line() + "\n�wCODE BREAKER�x");


		int[] answer = new int[4];																	//�����̔z��A���͂̔z���p��
		int[] input = new int[4];
		int hit = 0;
		int blow = 0;
		int count = 0;

		for (int i = 0; i < answer.length; i++) {													//�����_����4����ݒ�
			boolean flag = false;
			answer[i] = (int)(Math.random() * 10);

			do {																						//�d��������΂�����x�ݒ�
				flag = false;
				for (int j = i - 1; j >= 0; j--) {
					if (answer[i] == answer[j]) {
						flag = true;
						answer[i] = (int)(Math.random() * 10);
					}
				}
			} while (flag == true);					
		}

		while (true) {																				//�����̏d�����Ȃ����Ƃ��m�F������Q�[��������
			hit = 0;
			blow = 0;
			String show = "\n\t";		
			count++;

			println(line() + "\n�w" +count+ "th Challenge �x" );

			for (int i = 0; i < answer.length; i++) {												//�񓚕���
				System.out.print("  "+ (i+1) + "���ځF");
				int a = input();
				while (a < 0 || 9 < a) {
					println("0�`9�̐�������͂��Ă�������");
					a = input();
				}
				input[i] = a;
				show += input[i] + " ";
			}
			println(show + "\n");
		
			for (int i = 0; i < input.length; i++) {												//�񓚂ɍ��킹��hit�Ablow�ݒ�A�\��
				for (int j = 0; j < answer.length; j++) {
					if (i == j && input[i] == answer[j]) {
						hit++;
					} else if (input[i] == answer[j]) {
						blow++;
					}
				}
			}

			println("\tHIT�F" + hit + "\n\tBLOW�F" + blow);									//hit��blow�̐���\��

			if (hit == 4) {																			//hit�S�ŃN���A
				println("\n\n\n�w SUCCESS �x\n\n\n");
				clear();
			}
		}
	}



	public static void rute3() {														//�������[�v����肽�������̃��[�g
		enter();
		for(int i =0; i==i; i++){
			System.out.print("Zzzzzzz ");
		}
	}

	public static boolean isPrime(int n) {												//�f���`�F�b�N���\�b�h
		if (n < 2) return true;
		if (n == 2) return false;
		if (n % 2 == 0) return true;

		for (int i = 3; i * i <= n; i += 2) {				//�����̐����ɑ΂��A�f���Ȃ��false,�f������Ȃ����true��Ԃ�
			if (n % i == 0) return true;
		}
		return false;
	}
	
	public static void timer() {														//�^�C�}�[���\�b�h
		Timer timer = new Timer(false);
		TimerTask task = new TimerTask() {
		@Override
			public void run() {
				println("\n\n�n���͖łт��B���^���^���Ă������Ȃ��̂����ł��B���[���B");
				timer.cancel();
				exit();
			}
		};
		timer.schedule(task,180000);
	}

	public static void enter() {														//�G���^�[���̓��\�b�h
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try{
			print("��");
			String line = reader.readLine();
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	public static int input() {															//���l���̓��\�b�h
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try{
			int i = Integer.parseInt(reader.readLine());
			return i;
		} catch (NumberFormatException e) {
			return -1;
		} catch (IOException e) {
			return -1;
		}
	}

	public static void print(String s) {												//�o�̓��\�b�h
		System.out.print(s);
	}

	public static void println(String s) {												//�o�̓��\�b�h
		System.out.println(s);
	}

	public static void println(String[] s) {
		for (int i = 0; i < s.length; i++ ) {
			System.out.println(s[i]);
		}
	}

	public static String line() {														//line�o�̓��\�b�h
		return "\n-----------------------------------------------------------------------------------\n";
	}

	public static void clear() {														//�N���A���[�g�A�G���f�B���O
		enter();
		String[] ending = {"\n\t\t �n���͋~��ꂽ�B\n\n",line(),
							"\n\n\t\t   C L E A R  \n\n\n",line()};
		println(ending);
		System.exit(0);
	}

	public static void exit() {															//���Ԑ؂�G���h
		println("\n  E N D  \n"+
" �@ �@ �@    �@ . -�]�j�P�j�]- .\n"+
"�@�@�@ �@ �@ �Q�^�@�@�@�@�@ �@ �_�Q\n"+
"    �@����P�@/�@�@�@ �@   �@ �@ ', �P��\n"+
"�@   �@�@ �P7'' �\�\ �Q�Q�Q �\�\ ���P\n"+
"�\�\�\�@��,,i�@; �@�@ �@�@�@ `.� .��r��\�\�\�\�\�\\n"+
"�@�@  �@ �@ /\\��'jl|���=ҁ@i;_,,�,,i| ���U�\n"+
"�@�@�@�@�@�@ �M`�]�S:;!I�� �V!i���-' ^\n"
);
		System.exit(0);
	}		
}